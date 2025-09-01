package com.anushka.roommigrationdemo1

import android.content.Context
import androidx.room.*
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Student::class],
    version = 6,
    autoMigrations = [AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5, spec = StudentDatabase.Migration4T05::class),
        AutoMigration(from = 5, to = 6, spec = StudentDatabase.Migration5To6::class)]
)
abstract class StudentDatabase : RoomDatabase() {

    abstract val subscriberDAO: StudentDAO

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null
        fun getInstance(context: Context): StudentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        "student_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    @RenameColumn(
        tableName = "student_info",
        fromColumnName = "course_name",
        toColumnName = "subject_name"
    )
    class Migration4T05 : AutoMigrationSpec

    @DeleteColumn(tableName = "student_info", columnName = "student_age")
    class Migration5To6 : AutoMigrationSpec
}

