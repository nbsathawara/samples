package com.nbs.unitconverter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [ConversionResult::class]
)
abstract class UnitConverterDataBase : RoomDatabase() {

    abstract val conversionResultDao: ConversionResultDao

}