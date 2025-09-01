package com.nbs.unitconverter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_result")
data class ConversionResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "result_id")
    val id: Int,
    @ColumnInfo(name = "result_msg1")
    val msg1: String,
    @ColumnInfo(name = "result_msg2")
    val msg2: String,
)
