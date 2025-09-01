package com.example.roomdemo.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

@Database(
    version = 4, entities = [Subscriber::class],
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = SubscriberDatabase.Migration1to2::class
        ),
        AutoMigration(
            from = 2,
            to = 3,
            spec = SubscriberDatabase.Migration2to3::class
        ),
        AutoMigration(
            from = 3,
            to = 4,
            spec = SubscriberDatabase.Migration3to4::class
        )
    ]
)
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

    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_id",
        toColumnName = "id",
    )
    class Migration1to2 : AutoMigrationSpec

    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_name",
        toColumnName = "name",
    )
    class Migration2to3 : AutoMigrationSpec

    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_email",
        toColumnName = "email",
    )
    class Migration3to4 : AutoMigrationSpec
}