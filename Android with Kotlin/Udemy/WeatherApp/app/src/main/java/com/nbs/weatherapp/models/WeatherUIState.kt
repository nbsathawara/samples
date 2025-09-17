package com.nbs.weatherapp.models

sealed class WeatherUIState {
    object Loading : WeatherUIState()
    data class Success(val weatherInfo: WeatherInfo) : WeatherUIState()
    data class Error(val message: String) : WeatherUIState()
}