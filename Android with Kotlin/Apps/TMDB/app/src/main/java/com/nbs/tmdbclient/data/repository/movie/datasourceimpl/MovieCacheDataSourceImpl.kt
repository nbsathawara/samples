package com.nbs.tmdbclient.data.repository.movie.datasourceimpl

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource


class MovieCacheDataSourceImpl : MovieCacheDataSource {
    private val movies = ArrayList<Movie>()
    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override suspend fun getMoviesFromCache(): List<Movie> {
        return movies
    }
}