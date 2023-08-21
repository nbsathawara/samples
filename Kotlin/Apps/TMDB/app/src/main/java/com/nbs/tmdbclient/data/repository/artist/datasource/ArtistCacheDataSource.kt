package com.nbs.tmdbclient.data.repository.artist.datasource

import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.movie.Movie

interface ArtistCacheDataSource {

    suspend fun saveArtistsToCache(artists: List<Artist>)

    suspend fun getArtistsFromCache(): List<Artist>
}