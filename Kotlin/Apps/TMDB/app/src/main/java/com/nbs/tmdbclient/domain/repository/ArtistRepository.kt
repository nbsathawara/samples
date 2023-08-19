package com.nbs.tmdbclient.domain.repository

import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.movie.Movie

interface ArtistRepository {

    suspend fun  getArtists():List<Artist>?

    suspend fun  updateArtists():List<Artist>?
}