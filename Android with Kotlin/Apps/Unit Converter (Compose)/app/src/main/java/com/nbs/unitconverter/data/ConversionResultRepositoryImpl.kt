package com.nbs.unitconverter.data

import com.nbs.unitconverter.database.ConversionResult
import com.nbs.unitconverter.database.ConversionResultDao
import kotlinx.coroutines.flow.Flow

class ConversionResultRepositoryImpl(private val conversionResultDao: ConversionResultDao) :
    ConversionResultRepository {

    override suspend fun insertConversionResult(conversionResult: ConversionResult) {
        conversionResultDao.insertResult(conversionResult)
    }

    override suspend fun deleteConversionResult(conversionResult: ConversionResult) {
        conversionResultDao.deleteResult(conversionResult)
    }

    override suspend fun deleteAll() {
        conversionResultDao.deleteAll()
    }

    override fun getAllConversionResult(): Flow<List<ConversionResult>> {
        return conversionResultDao.getAllResult()
    }
}