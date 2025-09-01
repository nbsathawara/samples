package com.nbs.tmdbclient.presentation.di.artist

import com.nbs.tmdbclient.domain.usecase.GetArtistsUseCase
import com.nbs.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.nbs.tmdbclient.presentation.artist.ArtistViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ArtistModule {

    @Provides
    @ActivityScoped
    fun provideArtistViewModuleFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModel.ArtistViewModelFactory =
        ArtistViewModel.ArtistViewModelFactory(getArtistsUseCase, updateArtistsUseCase)
}
