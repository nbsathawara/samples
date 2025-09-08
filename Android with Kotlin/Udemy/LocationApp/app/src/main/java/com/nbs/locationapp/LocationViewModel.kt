package com.nbs.locationapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LocationViewModel : ViewModel() {

    private val _msg = mutableStateOf("Location not available!!!!")
    val msg: State<String> = _msg
    fun updateMsg(msg: String) {
        _msg.value = msg
    }

    private val _locationData = mutableStateOf<LocationData?>(null)
    val locationData: State<LocationData?> = _locationData
    fun updateLocation(locationData: LocationData) {
        _locationData.value = locationData
        _msg.value = "Address: ${locationData.lat}, ${locationData.lng}"
    }
}