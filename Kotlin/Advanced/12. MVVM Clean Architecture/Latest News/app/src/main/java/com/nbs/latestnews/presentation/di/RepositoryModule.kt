package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.data.repository.NewsRepositoryImpl
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.domain.repository.NewsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    fun providesNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository =
        NewsRepositoryImpl(newsRemoteDataSource)
}