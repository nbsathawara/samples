package com.nbs.tmdbclient.data.repository.tvshow.datasource

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.movie.MovieList
import com.nbs.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows(): Response<TvShowList>
}