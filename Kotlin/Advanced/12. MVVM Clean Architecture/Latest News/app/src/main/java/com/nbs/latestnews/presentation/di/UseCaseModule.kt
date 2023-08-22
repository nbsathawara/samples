package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.domain.repository.NewsRepository
import com.nbs.latestnews.domain.usecase.*
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

    @Provides
    @Singleton
    fun providesSaveNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase =
        SaveNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun providesGetSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase =
        GetSavedNewsUseCase(newsRepository)

    @Provides
    @Singleton
    fun providesDeleteSavedNewsUseCase(newsRepository: NewsRepository): DeleteSavedNewsUseCase =
        DeleteSavedNewsUseCase(newsRepository)
}