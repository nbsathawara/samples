package com.nbs.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nbs.tmdbclient.data.model.movie.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDao: MovieDao
    private lateinit var tmdbDatabase: TMDBDatabase

    @Before
    fun setUp() {
        tmdbDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        movieDao = tmdbDatabase.movieDao()
    }

    @After
    fun tearDown() {
        tmdbDatabase.close()
    }

    @Test
    fun saveMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overview 1", "path 1", "date 1", "title1"),
            Movie(
                2, "overview 2", "path 2", "date 2", "title2"
            ),
            Movie(3, "overview 3", "path 3", "date 3", "title3")
        )

        movieDao.saveMovies(movies)

        val dbMovies = movieDao.getAllMovies()

        Truth.assertThat(dbMovies).isEqualTo(movies)
    }

    @Test
    fun deleteMovieTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overview 1", "path 1", "date 1", "title1"),
            Movie(
                2, "overview 2", "path 2", "date 2", "title2"
            ),
            Movie(3, "overview 3", "path 3", "date 3", "title3")
        )

        movieDao.saveMovies(movies)

        movieDao.deleteAllMovies()

        val dbMovies = movieDao.getAllMovies()

        Truth.assertThat(dbMovies).isEmpty()
    }
}