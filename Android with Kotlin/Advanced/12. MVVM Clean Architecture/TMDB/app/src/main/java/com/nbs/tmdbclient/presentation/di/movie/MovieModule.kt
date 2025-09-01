package com.nbs.tmdbclient.presentation.di.movie

import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.nbs.tmdbclient.presentation.movie.MovieViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModuleFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModel.MovieViewModelFactory =
        MovieViewModel.MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class MovieScope