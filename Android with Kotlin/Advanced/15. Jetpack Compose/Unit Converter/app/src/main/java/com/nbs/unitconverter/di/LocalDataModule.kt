package com.nbs.unitconverter.di

import com.nbs.unitconverter.data.ConversionResultRepository
import com.nbs.unitconverter.data.ConversionResultRepositoryImpl
import com.nbs.unitconverter.database.ConversionResultDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideConversionResultRepository(conversionResultDao: ConversionResultDao): ConversionResultRepository {
        return ConversionResultRepositoryImpl(conversionResultDao)
    }
}