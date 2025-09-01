package com.nbs.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.repository.movie.FakeMovieRepository
import com.nbs.tmdbclient.domain.repository.MovieRepository
import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.nbs.tmdbclient.getOrAwaitValue
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieViewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMoviesUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMoviesUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        movieViewModel = MovieViewModel(getMoviesUseCase, updateMoviesUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(1, "overview 1", "path 1", "date 1", "title1"))
        movies.add(
            Movie(
                2, "overview 2", "path 2", "date 2", "title2"
            )
        )

        val curMovies = movieViewModel.getMovies().getOrAwaitValue()

        Truth.assertThat(curMovies).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdatedList() {
        val movies = mutableListOf<Movie>()
        movies.add(Movie(3, "overview 3", "path 3", "date 3", "title3"))

        val updatedMovies = movieViewModel.updateMovies().getOrAwaitValue()

        Truth.assertThat(updatedMovies).isEqualTo(movies)
    }
}