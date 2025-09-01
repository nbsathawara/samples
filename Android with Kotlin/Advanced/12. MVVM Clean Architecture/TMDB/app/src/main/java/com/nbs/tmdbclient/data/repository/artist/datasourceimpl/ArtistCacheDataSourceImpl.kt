package com.nbs.tmdbclient.data.repository.artist.datasourceimpl

import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource


class ArtistCacheDataSourceImpl : ArtistCacheDataSource {
    private val artists = ArrayList<Artist>()
    override suspend fun saveArtistsToCache(movies: List<Artist>) {
        this.artists.clear()
        this.artists.addAll(artists)
    }

    override suspend fun getArtistsFromCache(): List<Artist> {
        return artists
    }
}