package com.nbs.postsapp.di

import com.nbs.postsapp.network.APIService
import com.nbs.postsapp.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: RetrofitClient): APIService {
        return retrofitClient.apiService
    }
}