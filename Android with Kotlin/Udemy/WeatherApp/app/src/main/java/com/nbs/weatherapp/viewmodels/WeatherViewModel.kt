package com.nbs.weatherapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.weatherapp.data.repository.WeatherRepository
import com.nbs.weatherapp.models.WeatherUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherUIState = MutableStateFlow<WeatherUIState>(WeatherUIState.Loading)
    val weatherUIState: StateFlow<WeatherUIState> = _weatherUIState.asStateFlow()

    private val _locations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _locations.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String>("")
    val selectedLocation: StateFlow<String?> = _selectedLocation.asStateFlow()

    init {
        fetchAllLocations()
    }

    private fun fetchAllLocations() {
        viewModelScope.launch {

            weatherRepository.getAllLocations().collect { locations ->
                _locations.value = locations
            }

            if (_locations.value.isNotEmpty()) {
                _selectedLocation.value = _locations.value[0]
                fetchWeatherInfo(_locations.value[0])
            }
        }
    }

    private fun fetchWeatherInfo(location: String) {
        _weatherUIState.value = WeatherUIState.Loading
        viewModelScope.launch {
            try {
                val weatherInfo = weatherRepository.getWeatherInfo(location)
                _weatherUIState.value = WeatherUIState.Success(weatherInfo)
            } catch (e: Exception) {
                _weatherUIState.value = WeatherUIState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun onLocationSelected(location: String) {
        _selectedLocation.value = location
        fetchWeatherInfo(location)
    }
}