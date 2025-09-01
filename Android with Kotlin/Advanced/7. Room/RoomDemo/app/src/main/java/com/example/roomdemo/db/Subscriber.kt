package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("subscriber_data_table")
data class Subscriber(
    @PrimaryKey(true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    var name: String,
    @ColumnInfo("email")
    var email: String
) {
}