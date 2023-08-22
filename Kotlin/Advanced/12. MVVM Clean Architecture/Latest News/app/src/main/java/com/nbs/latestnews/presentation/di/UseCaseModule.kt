package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.domain.repository.NewsRepository
import com.nbs.latestnews.domain.usecase.GetNewsHeadlinesUseCase
import com.nbs.latestnews.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun providesNewsHeadlineUseCase(newsRepository: NewsRepository): GetNewsHeadlinesUseCase =
        GetNewsHeadlinesUseCase(newsRepository)

    @Provides
    @Singleton
    fun providesSearchedNewsUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase =
        GetSearchedNewsUseCase(newsRepository)
}