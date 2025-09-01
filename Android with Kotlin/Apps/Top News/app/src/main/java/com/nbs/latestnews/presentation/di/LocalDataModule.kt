package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.data.db.ArticleDao
import com.nbs.latestnews.data.repository.datasource.NewsLocalDataSource
import com.nbs.latestnews.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun providesNewsLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource =
        NewsLocalDataSourceImpl(articleDao)
}