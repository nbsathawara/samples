package com.nbs.tmdbclient.domain.usecase

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.domain.repository.MovieRepository

class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.getMovies()
}