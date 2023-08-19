package com.nbs.tmdbclient.presentation.di.artist

import com.nbs.tmdbclient.presentation.artist.ArtistsActivity
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(artistsActivity: ArtistsActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ArtistSubComponent
    }
}