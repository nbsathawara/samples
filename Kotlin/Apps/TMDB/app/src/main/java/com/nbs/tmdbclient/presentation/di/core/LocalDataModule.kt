package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.data.db.ArtistDao
import com.nbs.tmdbclient.data.db.MovieDao
import com.nbs.tmdbclient.data.db.TMDBDatabase
import com.nbs.tmdbclient.data.db.TvShowDao
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.nbs.tmdbclient.data.repository.artist.datasourceimpl.ArtistLocalDataSourceImpl
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.nbs.tmdbclient.data.repository.movie.datasourceimpl.MovieLocalDataSourceImpl
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowLocalDataSourceImpl
import com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule() {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(movieDao)

    @Singleton
    @Provides
    fun provideTvShowLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource =
        TvShowLocalDataSourceImpl(tvShowDao)

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource =
        ArtistLocalDataSourceImpl(artistDao)

}