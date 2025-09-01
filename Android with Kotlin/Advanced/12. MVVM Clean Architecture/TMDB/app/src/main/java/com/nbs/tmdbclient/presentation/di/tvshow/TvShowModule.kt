package com.nbs.tmdbclient.presentation.di.tvshow

import com.nbs.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateTvShowsUseCase
import com.nbs.tmdbclient.presentation.tvshow.TvShowViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModuleFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModel.TvShowViewModelFactory =
        TvShowViewModel.TvShowViewModelFactory(getTvShowsUseCase, updateTvShowsUseCase)
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TvShowScope