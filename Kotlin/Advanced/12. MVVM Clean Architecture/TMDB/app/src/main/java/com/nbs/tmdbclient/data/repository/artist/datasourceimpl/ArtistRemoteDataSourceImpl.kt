package com.nbs.tmdbclient.data.repository.artist.datasourceimpl

import com.nbs.tmdbclient.data.api.TMDBService
import com.nbs.tmdbclient.data.model.artist.ArtistList
import com.nbs.tmdbclient.data.model.movie.MovieList
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class ArtistRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ArtistRemoteDataSource {

    override suspend fun getArtists(): Response<ArtistList> = tmdbService.getPopularArtists(apiKey)

}