package com.nbs.tmdbclient.data.repository.artist.datasource

import com.nbs.tmdbclient.data.model.artist.ArtistList
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistList>
}