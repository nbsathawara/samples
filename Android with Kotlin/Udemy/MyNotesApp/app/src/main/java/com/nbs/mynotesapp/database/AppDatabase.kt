package com.nbs.mynotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbs.mynotesapp.models.Note

@Database(
    version = 1,
    entities = [Note::class],
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
}