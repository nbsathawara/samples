package com.nbs.tmdbclient.data.repository.movie.datasource

import com.nbs.tmdbclient.data.model.movie.Movie

interface MovieCacheDataSource {

    suspend fun saveMoviesToCache(movies: List<Movie>)

    suspend fun getMoviesFromCache(): List<Movie>
}