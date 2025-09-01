package com.nbs.latestnews.presentation.di

import com.nbs.latestnews.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun providesNewsAdapter():NewsAdapter = NewsAdapter()
}