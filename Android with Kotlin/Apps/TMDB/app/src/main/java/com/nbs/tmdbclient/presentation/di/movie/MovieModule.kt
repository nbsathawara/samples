package com.nbs.tmdbclient.presentation.di.movie

import com.nbs.tmdbclient.domain.usecase.GetMoviesUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.nbs.tmdbclient.presentation.movie.MovieViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class MovieModule {

    @Provides
    @ActivityScoped
    fun provideMovieViewModuleFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModel.MovieViewModelFactory =
        MovieViewModel.MovieViewModelFactory(getMoviesUseCase, updateMoviesUseCase)
}
