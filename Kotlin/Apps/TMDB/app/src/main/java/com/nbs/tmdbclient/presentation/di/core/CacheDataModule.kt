package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.nbs.tmdbclient.data.repository.artist.datasourceimpl.ArtistCacheDataSourceImpl
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.nbs.tmdbclient.data.repository.movie.datasourceimpl.MovieCacheDataSourceImpl
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {
    @Singleton
    @Provides
    fun provideMovieCacheDatasource(): MovieCacheDataSource =
        MovieCacheDataSourceImpl()

    @Singleton
    @Provides
    fun provideTvShowCacheDatasource(): TvShowCacheDataSource =
        TvShowCacheDataSourceImpl()

    @Singleton
    @Provides
    fun provideArtistCacheDatasource(): ArtistCacheDataSource =
        ArtistCacheDataSourceImpl()
}