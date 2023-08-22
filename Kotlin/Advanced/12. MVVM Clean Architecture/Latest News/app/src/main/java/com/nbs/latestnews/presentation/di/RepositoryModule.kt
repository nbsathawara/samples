package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.data.repository.NewsRepositoryImpl
import com.nbs.latestnews.data.repository.datasource.NewsLocalDataSource
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository =
        NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
}