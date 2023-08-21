package com.nbs.tmdbclient.presentation.di.tvshow

import com.nbs.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.nbs.tmdbclient.presentation.tvshow.TvShowViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope

@Module
@InstallIn(ActivityComponent::class)
class TvShowModule {

    @Provides
    @ActivityScoped
    fun provideTvShowViewModuleFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModel.TvShowViewModelFactory =
        TvShowViewModel.TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)
}