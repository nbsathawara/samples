package com.nbs.weatherapp.data.repository

import com.nbs.weatherapp.models.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherInfo(location: String): WeatherInfo

    fun getAllLocations(): Flow<List<String>>
}
