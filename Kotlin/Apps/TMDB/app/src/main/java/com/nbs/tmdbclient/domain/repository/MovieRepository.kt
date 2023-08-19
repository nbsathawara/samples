package com.nbs.tmdbclient.domain.repository

import com.nbs.tmdbclient.data.model.movie.Movie

interface MovieRepository {

    suspend fun  getMovies():List<Movie>?

    suspend fun  updateMovies():List<Movie>?
}