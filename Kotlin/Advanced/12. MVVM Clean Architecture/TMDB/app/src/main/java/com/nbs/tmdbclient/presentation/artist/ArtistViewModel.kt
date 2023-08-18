package com.nbs.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.nbs.tmdbclient.domain.usecase.GetArtistsUseCase
import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase

class ArtistViewModel(
    private val getArtistsUseCase: GetArtistsUseCase,
    private val updateArtistsUseCase: UpdateArtistsUseCase
) : ViewModel() {

    fun getArtists() = liveData {
        val artists = getArtistsUseCase.execute()
        emit(artists)
    }

    fun updateArtists() = liveData {
        val newArtists = updateArtistsUseCase.execute()
        emit(newArtists)
    }

    class ArtistViewModelFactory(
        private val getArtistsUseCase: GetArtistsUseCase,
        private val updateArtistsUseCase: UpdateArtistsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArtistViewModel(getArtistsUseCase, updateArtistsUseCase) as T
        }
    }
}