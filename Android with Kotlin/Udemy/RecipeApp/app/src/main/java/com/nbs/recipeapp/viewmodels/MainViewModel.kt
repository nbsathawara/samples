package com.nbs.recipeapp.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.recipeapp.models.Category
import com.nbs.recipeapp.services.recipeService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    data class RecipeState(
        val loading: Boolean = true,
        val error: String? = null,
        val categories: List<Category> = emptyList()
    )

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = null,
                    categories = response.categories
                )
            } catch (e: Exception) {
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching categories ${e.message}"
                )
            }
        }
    }
}