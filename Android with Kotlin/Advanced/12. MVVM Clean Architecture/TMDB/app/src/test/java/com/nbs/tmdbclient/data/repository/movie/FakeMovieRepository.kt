package com.nbs.tmdbclient.data.repository.movie

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository : MovieRepository {

    private val movies = mutableListOf<Movie>()

    init {
        movies.add(Movie(1, "overview 1", "path 1", "date 1", "title1"))
        movies.add(
            Movie(
                2, "overview 2", "path 2", "date 2", "title2"
            )
        )
    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.add(Movie(3, "overview 3", "path 3", "date 3", "title3"))
        return movies
    }
}