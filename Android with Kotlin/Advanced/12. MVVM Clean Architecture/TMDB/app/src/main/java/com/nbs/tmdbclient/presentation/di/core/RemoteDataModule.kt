package com.nbs.tmdbclient.presentation.di.core

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
import javax.inject.Singleton

@Module
class RemoteDataModule(private val apiKey: String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(tmdbService, apiKey)

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource =
        TvShowRemoteDataSourceImpl(tmdbService, apiKey)

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource =
        ArtistRemoteDataSourceImpl(tmdbService, apiKey)
}