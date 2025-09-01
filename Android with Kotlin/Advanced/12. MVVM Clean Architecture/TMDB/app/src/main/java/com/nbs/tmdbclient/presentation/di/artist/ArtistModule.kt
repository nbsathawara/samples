package com.nbs.tmdbclient.presentation.di.artist

import com.nbs.tmdbclient.domain.usecase.GetArtistsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.nbs.tmdbclient.presentation.artist.ArtistViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModuleFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModel.ArtistViewModelFactory =
        ArtistViewModel.ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
}

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ArtistScope