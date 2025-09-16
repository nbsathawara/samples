package com.nbs.moviesapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nbs.moviesapp.data.Constants
import com.nbs.moviesapp.models.Movie
import com.nbs.moviesapp.models.TMDBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MovieViewModel(tmdbRepository: TMDBRepository) : ViewModel() {

    var isLoading by mutableStateOf(true)

    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies: StateFlow<List<Movie>> = _movies

    private val _onlineMovies = MutableStateFlow(emptyList<Movie>())

    private val _offlineMovies = MutableStateFlow(emptyList<Movie>())

    init {
        isLoading = true
        viewModelScope.launch {
            try {
                Log.d("Debug : ", "Fetching movies from API")
                val onlineMovies = tmdbRepository.getOnlineMovies(Constants.API_KEY)
                _onlineMovies.value = onlineMovies
                _movies.value = onlineMovies
                Log.d("Debug : ", "Movies fetched from API")
                isLoading = false
                withContext(Dispatchers.IO) {
                    //delay(3000)
                    tmdbRepository.insertMovies(movies.value)
                    Log.d("Debug : ", "Movies inserted into database")
                }
            } catch (e: Exception) {
                Log.d("Debug : ", "Fetching movies from database")
                tmdbRepository.getOfflineMovies().collect { offlineMovies ->
                    _offlineMovies.value = offlineMovies
                    _movies.value = offlineMovies
                    Log.d("Debug : ", "Movies fetched from database")
                    isLoading = false
                }
            } finally {
                isLoading = false
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