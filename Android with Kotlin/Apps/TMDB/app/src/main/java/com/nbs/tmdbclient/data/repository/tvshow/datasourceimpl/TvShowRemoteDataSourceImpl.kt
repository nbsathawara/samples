package com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl

import com.nbs.tmdbclient.data.api.TMDBService
import com.nbs.tmdbclient.data.model.movie.MovieList
import com.nbs.tmdbclient.data.model.tvshow.TvShowList
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
) : TvShowRemoteDataSource {

    override suspend fun getTvShows(): Response<TvShowList> = tmdbService.getPopularTvShows(apiKey)
}