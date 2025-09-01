package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.nbs.tmdbclient.data.repository.artist.datasourceimpl.ArtistRepositoryImpl
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.nbs.tmdbclient.data.repository.movie.datasourceimpl.MovieRepositoryImpl
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl.TvShowRepositoryImpl
import com.nbs.tmdbclient.domain.repository.ArtistRepository
import com.nbs.tmdbclient.domain.repository.MovieRepository
import com.nbs.tmdbclient.domain.repository.TvShowRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource,
        movieCacheDataSource: MovieCacheDataSource
    ): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, movieCacheDataSource)

    @Singleton
    @Provides
    fun provideTvShowRepository(
        tvShowRemoteDataSource: TvShowRemoteDataSource,
        tvShowLocalDataSource: TvShowLocalDataSource,
        tvShowCacheDataSource: TvShowCacheDataSource
    ): TvShowRepository =
        TvShowRepositoryImpl(tvShowRemoteDataSource, tvShowLocalDataSource, tvShowCacheDataSource)

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDataSource: ArtistRemoteDataSource,
        artistLocalDataSource: ArtistLocalDataSource,
        artistCacheDataSource: ArtistCacheDataSource
    ): ArtistRepository =
        ArtistRepositoryImpl(artistRemoteDataSource, artistLocalDataSource, artistCacheDataSource)


}