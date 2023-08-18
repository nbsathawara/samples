package com.nbs.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow

@Database(
    version = 1,
    entities = [Movie::class, TvShow::class, Artist::class],
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TvShowDao

    abstract fun artistDao(): ArtistDao

}