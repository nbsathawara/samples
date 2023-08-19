package com.nbs.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.nbs.tmdbclient.data.db.ArtistDao
import com.nbs.tmdbclient.data.db.MovieDao
import com.nbs.tmdbclient.data.db.TMDBDatabase
import com.nbs.tmdbclient.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TMDBDatabase =
        Room.databaseBuilder(context, TMDBDatabase::class.java, "tmdbclientdb")
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