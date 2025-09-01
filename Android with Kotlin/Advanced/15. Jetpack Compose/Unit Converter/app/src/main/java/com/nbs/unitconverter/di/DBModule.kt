package com.nbs.unitconverter.di

import android.app.Application
import androidx.room.Room
import com.nbs.unitconverter.database.ConversionResultDao
import com.nbs.unitconverter.database.UnitConverterDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideUnitConverterDataBase(app: Application): UnitConverterDataBase {
        return Room.databaseBuilder(
            context = app.applicationContext,
            UnitConverterDataBase::class.java,
            "unit_converter_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConversionResultDao(unitConverterDataBase: UnitConverterDataBase): ConversionResultDao {
        return unitConverterDataBase.conversionResultDao
    }
}