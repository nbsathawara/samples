package com.nbs.unitconverter.data

data class Conversion(
    val id: Int,
    val desc: String,
    val convertFrom: String,
    val convertTo: String,
    val multiplyBy:Double
)
