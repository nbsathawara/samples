package com.example.recyclerviewdemo

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

data class Person(
    val name: String, val age: Int, val gender: String,
    val birthDate: Date
) : Comparable<Person> {
    fun getBirthDate(): String {
        return SimpleDateFormat("dd.MM.yyyy").format(birthDate)
    }

    override fun compareTo(other: Person): Int {
        return birthDate.compareTo(other.birthDate)
    }
}
