package com.nbs.tmdbclient.presentation.di.core

import com.nbs.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.nbs.tmdbclient.presentation.di.movie.MovieSubComponent
import com.nbs.tmdbclient.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CacheDataModule::class,
        DatabaseModule::class,
        LocalDataModule::class,
        NetworkModule::class,
        RemoteDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
interface AppComponent {

    fun movieSubComponent(): MovieSubComponent.Factory

    fun tvShowSubComponent(): TvShowSubComponent.Factory

    fun artistSubComponent(): ArtistSubComponent.Factory
}