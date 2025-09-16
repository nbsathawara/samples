package com.nbs.moviesapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nbs.moviesapp.data.Constants
import com.nbs.moviesapp.models.Movie
import com.nbs.moviesapp.models.TMDBRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(tmdbRepository: TMDBRepository) : ViewModel() {
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    private val _onlineMovies = MutableStateFlow(emptyList<Movie>())
    val onlineMovies: StateFlow<List<Movie>> = _onlineMovies

    init {
        viewModelScope.launch {
            try {
                val onlineMovies = tmdbRepository.getPopularMovies(Constants.API_KEY)
                _onlineMovies.value = onlineMovies
                _movies.value = onlineMovies
            } catch (e: Exception) {

            }
        }
    }
}

class MovieViewModelFactory(private val tmdbRepository: TMDBRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(tmdbRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}