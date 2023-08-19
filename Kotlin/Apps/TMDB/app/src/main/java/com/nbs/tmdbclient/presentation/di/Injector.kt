package com.nbs.tmdbclient.presentation.di

import com.nbs.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.nbs.tmdbclient.presentation.di.movie.MovieSubComponent
import com.nbs.tmdbclient.presentation.di.tvshow.TvShowSubComponent

interface Injector {

    fun createMovieSubComponent(): MovieSubComponent

    fun createTvShowSubComponent(): TvShowSubComponent

    fun createArtistSubComponent(): ArtistSubComponent

}