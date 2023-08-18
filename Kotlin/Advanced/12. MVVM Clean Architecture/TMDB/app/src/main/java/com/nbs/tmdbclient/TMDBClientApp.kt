package com.nbs.tmdbclient

import android.app.Application
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.presentation.di.Injector
import com.nbs.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.nbs.tmdbclient.presentation.di.core.*
import com.nbs.tmdbclient.presentation.di.movie.MovieSubComponent
import com.nbs.tmdbclient.presentation.di.tvshow.TvShowSubComponent

class TMDBClientApp : Application(), Injector {

    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(Utils.apiBaseUrl))
            .remoteDataModule(RemoteDataModule(Utils.tmdbAPIKey))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
       return appComponent.movieSubComponent().create()
    }

    override fun createTvShowSubComponent(): TvShowSubComponent {
        return appComponent.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponent.artistSubComponent().create()
    }
}