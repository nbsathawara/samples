package com.nbs.dailypulse.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getAndroidDatabaseBuilder(context: Context): RoomDatabase.Builder<DailyPulseDatabase> {
    val dbFile = context.getDatabasePath("dailypulse.db")
    return Room.databaseBuilder<DailyPulseDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}