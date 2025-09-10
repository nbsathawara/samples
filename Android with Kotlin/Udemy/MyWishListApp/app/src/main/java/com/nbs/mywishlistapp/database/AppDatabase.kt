package com.nbs.mywishlistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nbs.mywishlistapp.data.models.Wish
import com.nbs.mywishlistapp.database.dao.WishDao

@Database(entities = [Wish::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build().also { INSTANCE = it }
//            }
//        }
//    }

    abstract fun wishDao(): WishDao

}