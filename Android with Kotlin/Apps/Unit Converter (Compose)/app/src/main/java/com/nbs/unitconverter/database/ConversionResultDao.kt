package com.nbs.unitconverter.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionResultDao {

    @Query("Select * from conversion_result")
    fun getAllResult(): Flow<List<ConversionResult>>

    @Insert
    suspend fun insertResult(conversionResult: ConversionResult)

    @Delete
    suspend fun deleteResult(conversionResult: ConversionResult)

    @Query("Delete from conversion_result")
    suspend fun deleteAll()

}