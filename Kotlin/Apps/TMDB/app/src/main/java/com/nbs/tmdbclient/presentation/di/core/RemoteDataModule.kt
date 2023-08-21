package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.api.TMDBService
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.nbs.tmdbclient.data.repository.artist.datasourceimpl.ArtistRemoteDataSourceImpl
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.nbs.tmdbclient.data.repository.movie.datasourceimpl.MovieRemoteDataSourceImpl
import com.nbs.tmdbclient.data.repository.movie.datasourceimpl.MovieRepositoryImpl
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule() {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(tmdbService, Utils.tmdbAPIKey)

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tmdbService, Utils.tmdbAPIKey)

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource =
        ArtistRemoteDataSourceImpl(tmdbService, Utils.tmdbAPIKey)
}