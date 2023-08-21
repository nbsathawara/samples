package com.nbs.tmdbclient.data.repository.artist.datasourceimpl

import android.util.Log
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.nbs.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.nbs.tmdbclient.domain.repository.ArtistRepository

class ArtistRepositoryImpl(
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository {

    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newArtists = getArtistsFromAPI()

        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newArtists)

        artistCacheDataSource.saveArtistsToCache(newArtists)

        return newArtists
    }

    private suspend fun getArtistsFromCache(): List<Artist> {
        lateinit var artists: List<Artist>
        try {
            artists = artistCacheDataSource.getArtistsFromCache()
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        if (artists.isNotEmpty()) {
            Log.i(Utils.logTagName, "Getting data from Cache...")
            return artists
        } else {
            artists = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artists)
            return artists
        }
    }

    private suspend fun getArtistsFromDB(): List<Artist> {
        lateinit var artists: List<Artist>
        try {
            artists = artistLocalDataSource.getArtistsFromDB()
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        if (artists.isNotEmpty()) {
            Log.i(Utils.logTagName, "Getting data from DB...")
            return artists
        } else {
            artists = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artists)
            return artists
        }
    }

    private suspend fun getArtistsFromAPI(): List<Artist> {
        lateinit var artists: List<Artist>
        try {
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if (body != null) {
                artists = body.artists
            }
        } catch (e: java.lang.Exception) {
            Log.i(Utils.logTagName, e.message.toString())
        }
        Log.i(Utils.logTagName, "Getting data from Server...")
        return artists
    }
}