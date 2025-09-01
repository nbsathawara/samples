package com.nbs.tmdbclient.domain.usecase

import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute(): List<Artist>? = artistRepository.getArtists()
}