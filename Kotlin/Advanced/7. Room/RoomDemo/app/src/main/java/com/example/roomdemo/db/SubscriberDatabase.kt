package com.example.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Subscriber::class])
abstract class SubscriberDatabase : RoomDatabase() {
    abstract fun subscriberDao(): SubscriberDao

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance: SubscriberDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, SubscriberDatabase::class.java,
                        "subscriber_data_databse"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}