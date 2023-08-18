package com.nbs.tmdbclient.presentation.di.movie

import com.nbs.tmdbclient.presentation.movie.MoviesActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(moviesActivity: MoviesActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieSubComponent
    }
}