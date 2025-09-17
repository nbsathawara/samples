package com.nbs.weatherapp.data.repository

import com.nbs.weatherapp.models.WeatherInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    override suspend fun getWeatherInfo(location: String): WeatherInfo {
        return weatherData[location] ?: WeatherInfo(location, 0, "Unknown")
    }

    override fun getAllLocations(): Flow<List<String>> {
        return flowOf(weatherData.keys.toList())
    }

    private val weatherData = mapOf(
        "New York" to WeatherInfo("New York", 25, "Sunny"),
        "London" to WeatherInfo("London", 20, "Cloudy"),
        "Tokyo" to WeatherInfo("Tokyo", 30, "Rainy"),
        "Paris" to WeatherInfo("Paris", 22, "Partly cloudy"),
        "Sydney" to WeatherInfo("Sydney", 28, "Sunny"),
        "Rio de Janeiro" to WeatherInfo("Rio de Janeiro", 27, "Sunny"),
        "Moscow" to WeatherInfo("Moscow", 24, "Sunny"),
        "Berlin" to WeatherInfo("Berlin", 23, "Sunny"),
        "Dubai" to WeatherInfo("Dubai", 26, "Sunny"),
        "Mumbai" to WeatherInfo("Mumbai", 29, "Sunny"),
        "Beijing" to WeatherInfo("Beijing", 21, "Sunny"),
        "Shanghai" to WeatherInfo("Shanghai", 27, "Sunny"),
        "São Paulo" to WeatherInfo("São Paulo", 26, "Sunny"),
        "Mexico City" to WeatherInfo("Mexico City", 25, "Sunny"),
        "Cairo" to WeatherInfo("Cairo", 24, "Sunny"),
        "Buenos Aires" to WeatherInfo("Buenos Aires", 23, "Sunny"),
        "Istanbul" to WeatherInfo("Istanbul", 22, "Sunny"),
        "Lagos" to WeatherInfo("Lagos", 21, "Sunny"),
        "Kinshasa" to WeatherInfo("Kinshasa", 20, "Sunny"),
        "Johannesburg" to WeatherInfo("Johannesburg", 19, "Sunny"),
        "Khartoum" to WeatherInfo("Khartoum", 18, "Sunny"),
        "Dhaka" to WeatherInfo("Dhaka", 17, "Sunny"),
        "Cairo" to WeatherInfo("Cairo", 16, "Sunny"),
        "Nairobi" to WeatherInfo("Nairobi", 15, "Sunny"),
        "Dar es Salaam" to WeatherInfo("Dar es Salaam", 14, "Sunny"),
        "Accra" to WeatherInfo("Accra", 13, "Sunny"),
        "Luanda" to WeatherInfo("Luanda", 12, "Sunny"),
        "Casablanca" to WeatherInfo("Casablanca", 11, "Sunny"),
        "Addis Ababa" to WeatherInfo("Addis Ababa", 10, "Sunny"),
        "Mogadishu" to WeatherInfo("Mogadishu", 9, "Sunny"),
        "Nairobi" to WeatherInfo("Nairobi", 8, "Sunny"),
        "Dar es Salaam" to WeatherInfo("Dar es Salaam", 7, "Sunny"),
    )
}