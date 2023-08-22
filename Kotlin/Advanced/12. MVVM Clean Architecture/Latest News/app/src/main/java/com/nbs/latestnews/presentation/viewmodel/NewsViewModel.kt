package com.nbs.latestnews.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.data.model.NewsAPIResponse
import com.nbs.latestnews.data.util.Resource
import com.nbs.latestnews.data.util.Utils
import com.nbs.latestnews.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {

    val newsHeadlines: MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            try {
                if (Utils.isNetworkAvailable(app)) {
                    val apiResponse = getNewsHeadlinesUseCase.execute(country, page)
                    newsHeadlines.postValue(apiResponse)
                } else {
                    newsHeadlines.postValue(Resource.Error("Network not available..."))
                }
            } catch (e: Exception) {
                newsHeadlines.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    val searchedNewsList: MutableLiveData<Resource<NewsAPIResponse>> = MutableLiveData()
    fun searchNews(country: String, searchQuery: String, page: Int) {
        viewModelScope.launch {
            searchedNewsList.postValue(Resource.Loading())
            try {
                if (Utils.isNetworkAvailable(app)) {
                    val apiResponse = getSearchedNewsUseCase.execute(country, page, searchQuery)
                    searchedNewsList.postValue(apiResponse)
                } else {
                    searchedNewsList.postValue(Resource.Error("Network not available..."))
                }
            } catch (e: Exception) {
                searchedNewsList.postValue(Resource.Error(e.message.toString()))
            }
        }
    }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            saveNewsUseCase.execute(article)
        }
    }

    fun getSavedNewsArticles() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteSavedNewsArticle(article: Article) {
        viewModelScope.launch {
            deleteSavedNewsUseCase.execute(article)
        }
    }
}

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val searchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUseCase,
            searchedNewsUseCase,
            saveNewsUseCase,
            getSavedNewsUseCase,
            deleteSavedNewsUseCase
        ) as T
    }
}