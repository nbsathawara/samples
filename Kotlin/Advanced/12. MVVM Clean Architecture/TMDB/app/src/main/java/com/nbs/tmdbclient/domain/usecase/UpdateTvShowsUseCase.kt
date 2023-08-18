package com.nbs.tmdbclient.domain.usecase

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.domain.repository.MovieRepository

class UpdateTvShowsUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}