package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.domain.repository.ArtistRepository
import com.nbs.tmdbclient.domain.repository.MovieRepository
import com.nbs.tmdbclient.domain.repository.TvShowRepository
import com.nbs.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase =
        UpdateMoviesUseCase(movieRepository)

    @Singleton
    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase =
        GetTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase =
        UpdateTvShowsUseCase(tvShowRepository)

    @Singleton
    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository): GetArtistsUseCase =
        GetArtistsUseCase(artistRepository)

    @Singleton
    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase =
        UpdateArtistsUseCase(artistRepository)
}