package com.nbs.dailypulse.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.dailypulse.usecases.ArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

    private val _articleState = MutableStateFlow(ArticleState(isLoading = true))
    val articleState: StateFlow<ArticleState> = _articleState

    init {
        // 1. Immediately start listening to local Room DB (0ms delay UI update)
        observeArticles()

        // 2. Fetch fresh remote articles from API and update Room DB
        loadArticles()
    }

    private fun observeArticles() {
        viewModelScope.launch {
            articleUseCase.observeArticles().collect { cachedArticles ->
                _articleState.value = _articleState.value.copy(
                    articles = cachedArticles,
                    isLoading = cachedArticles.isEmpty() && _articleState.value.errorMessage == null
                )
            }
        }
    }

    fun loadArticles() {
        viewModelScope.launch {
            try {
                // Show loading spinner only if we don't have cached articles yet
                if (_articleState.value.articles.isEmpty()) {
                    _articleState.value = _articleState.value.copy(isLoading = true)
                }

                // Network request updates Room DB -> Room updates observeArticles()
                articleUseCase.fetchAndCacheArticles()

                _articleState.value =
                    _articleState.value.copy(isLoading = false, errorMessage = null)
            } catch (e: Exception) {
                _articleState.value = _articleState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to update articles from server"
                )
            }
        }
    }
}

data class ArticleState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

