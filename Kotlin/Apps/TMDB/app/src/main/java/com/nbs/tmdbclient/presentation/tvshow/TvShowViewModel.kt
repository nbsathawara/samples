package com.nbs.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
) : ViewModel() {

    fun getTvShows() = liveData {
        val tvShows = getTvShowsUseCase.execute()
        emit(tvShows)
    }

    fun updateTvShows() = liveData {
        val newTvShows = updateTvShowsUseCase.execute()
        emit(newTvShows)
    }

    class TvShowViewModelFactory(
        private val getTvShowsUseCase: GetTvShowsUseCase,
        private val updateTvShowsUseCase: UpdateTvShowsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TvShowViewModel(getTvShowsUseCase, updateTvShowsUseCase) as T
        }
    }
}