package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.domain.repository.ArtistRepository
import com.nbs.tmdbclient.domain.repository.MovieRepository
import com.nbs.tmdbclient.domain.repository.TvShowRepository
import com.nbs.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCase(movieRepository)

    
    @Provides
    fun provideUpdateMovieUseCase(movieRepository: MovieRepository): UpdateMoviesUseCase =
        UpdateMoviesUseCase(movieRepository)

    
    @Provides
    fun provideGetTvShowUseCase(tvShowRepository: TvShowRepository): GetTvShowsUseCase =
        GetTvShowsUseCase(tvShowRepository)

    
    @Provides
    fun provideUpdateTvShowUseCase(tvShowRepository: TvShowRepository): UpdateTvShowsUseCase =
        UpdateTvShowsUseCase(tvShowRepository)

    
    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository): GetArtistsUseCase =
        GetArtistsUseCase(artistRepository)

    
    @Provides
    fun provideUpdateArtistUseCase(artistRepository: ArtistRepository): UpdateArtistsUseCase =
        UpdateArtistsUseCase(artistRepository)
}