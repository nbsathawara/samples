package com.nbs.latestnews.presentation.di

import android.app.Application
import com.nbs.latestnews.domain.usecase.GetNewsHeadlinesUseCase
import com.nbs.latestnews.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun providesNewsViewModuleFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
    ): NewsViewModelFactory = NewsViewModelFactory(application, getNewsHeadlinesUseCase)
}