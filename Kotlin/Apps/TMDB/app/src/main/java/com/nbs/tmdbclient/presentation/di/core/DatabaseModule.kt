package com.nbs.tmdbclient.presentation.di.core

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.nbs.tmdbclient.TMDBClientApp
import com.nbs.tmdbclient.data.db.ArtistDao
import com.nbs.tmdbclient.data.db.MovieDao
import com.nbs.tmdbclient.data.db.TMDBDatabase
import com.nbs.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): TMDBDatabase =
        Room.databaseBuilder(app, TMDBDatabase::class.java, "tmdbclientdb")
            .build()


    @Singleton
    @Provides
    fun provideMovieDao(tmdbDatabase: TMDBDatabase): MovieDao =
        tmdbDatabase.movieDao()


    @Singleton
    @Provides
    fun provideTvShowDao(tmdbDatabase: TMDBDatabase): TvShowDao =
        tmdbDatabase.tvShowDao()


    @Singleton
    @Provides
    fun provideArtistDao(tmdbDatabase: TMDBDatabase): ArtistDao =
        tmdbDatabase.artistDao()

}