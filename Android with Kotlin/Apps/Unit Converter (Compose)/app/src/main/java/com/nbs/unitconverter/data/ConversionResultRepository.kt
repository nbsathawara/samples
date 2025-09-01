package com.nbs.unitconverter.data

import com.nbs.unitconverter.database.ConversionResult
import kotlinx.coroutines.flow.Flow

interface ConversionResultRepository {

    suspend fun insertConversionResult(conversionResult: ConversionResult)

    suspend fun deleteConversionResult(conversionResult: ConversionResult)

    suspend fun deleteAll()

    fun getAllConversionResult(): Flow<List<ConversionResult>>
}