package com.nbs.tmdbclient.data.repository.movie.datasource

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MovieList>
}