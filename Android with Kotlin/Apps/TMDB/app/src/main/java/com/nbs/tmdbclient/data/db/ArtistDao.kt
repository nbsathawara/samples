package com.nbs.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.tvshow.TvShow

@Dao
interface ArtistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(movies: List<Artist>)

    @Query("delete from popular_artists")
    suspend fun deleteAllArtists()

    @Query("select * from popular_artists")
    suspend fun getAllArtists(): List<Artist>
}