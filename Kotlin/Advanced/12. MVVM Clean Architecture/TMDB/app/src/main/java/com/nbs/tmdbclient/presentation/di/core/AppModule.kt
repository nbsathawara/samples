package com.nbs.tmdbclient.presentation.di.core

import android.content.Context
import com.nbs.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.nbs.tmdbclient.presentation.di.movie.MovieSubComponent
import com.nbs.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    subcomponents = [
        MovieSubComponent::class,
        TvShowSubComponent::class,
        ArtistSubComponent::class,
    ]
)
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context =
        context.applicationContext
}