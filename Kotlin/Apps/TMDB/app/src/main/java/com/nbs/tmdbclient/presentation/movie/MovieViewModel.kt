package com.nbs.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUseCase
) : ViewModel() {

    fun getMovies() = liveData {
        val movies = getMoviesUseCase.execute()
        emit(movies)
    }

    fun updateMovies() = liveData {
        val newMovies = updateMoviesUseCase.execute()
        emit(newMovies)
    }

    class MovieViewModelFactory(
        private val getMoviesUseCase: GetMoviesUseCase,
        private val updateMoviesUseCase: UpdateMoviesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(getMoviesUseCase, updateMoviesUseCase) as T
        }
    }
}