package com.nbs.tmdbclient.data.repository.movie.datasourceimpl

import android.util.Log
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.nbs.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newMovies = getMoviesFromAPI()

        movieLocalDataSource.clearAll()
        movieLocalDataSource.saveMoviesToDB(newMovies)

        movieCacheDataSource.saveMoviesToCache(newMovies)

        return newMovies
    }

    private suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movies: List<Movie>
        try {
            movies = movieCacheDataSource.getMoviesFromCache()
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        if (movies.isNotEmpty()) {
            Log.i(Utils.logTagName, "Getting data from Cache...")
            return movies
        } else {
            movies = getMoviesFromDB()
            movieCacheDataSource.saveMoviesToCache(movies)
            return movies
        }
    }

    private suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movies: List<Movie>
        try {
            movies = movieLocalDataSource.getMoviesFromDB()
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        if (movies.isNotEmpty()) {
            Log.i(Utils.logTagName, "Getting data from DB...")
            return movies
        } else {
            movies = getMoviesFromAPI()
            movieLocalDataSource.saveMoviesToDB(movies)
            return movies
        }
    }

    private suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movies: List<Movie>
        try {
            val response = movieRemoteDataSource.getMovies()
            val body = response.body()
            if (body != null) {
                movies = body.movies
            }
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        Log.i(Utils.logTagName, "Getting data from Server...")
        return movies
    }
}