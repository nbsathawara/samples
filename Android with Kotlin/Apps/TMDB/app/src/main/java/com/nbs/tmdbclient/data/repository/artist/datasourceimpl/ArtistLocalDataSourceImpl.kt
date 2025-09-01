package com.nbs.tmdbclient.data.repository.artist.datasourceimpl

import com.nbs.tmdbclient.data.db.ArtistDao
import com.nbs.tmdbclient.data.db.MovieDao
import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArtistLocalDataSourceImpl(private val artistDao: ArtistDao) : ArtistLocalDataSource {

    override suspend fun getArtistsFromDB(): List<Artist> {
        return artistDao.getAllArtists()
    }

    override suspend fun saveArtistsToDB(artists: List<Artist>) {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.saveArtists(artists)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtists()
        }
    }
}