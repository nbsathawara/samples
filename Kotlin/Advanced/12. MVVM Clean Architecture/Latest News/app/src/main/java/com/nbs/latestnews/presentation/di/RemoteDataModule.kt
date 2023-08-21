package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.data.api.NewsAPIService
import com.nbs.latestnews.data.repository.datasource.NewsRemoteDataSource
import com.nbs.latestnews.data.repository.datasourceimpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource =
        NewsRemoteDataSourceImpl(newsAPIService)
}