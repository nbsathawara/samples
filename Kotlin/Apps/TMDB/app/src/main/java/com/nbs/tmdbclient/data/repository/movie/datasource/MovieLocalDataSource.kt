package com.nbs.tmdbclient.data.repository.movie.datasource

import com.nbs.tmdbclient.data.model.movie.Movie

interface MovieLocalDataSource {

    suspend fun getMoviesFromDB():List<Movie>

    suspend fun saveMoviesToDB(movies:List<Movie>)

    suspend fun clearAll()
}