package com.nbs.shoppinglist.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.shoppinglist.data.Constants
import com.nbs.shoppinglist.models.GeocodingResult
import com.nbs.shoppinglist.models.LocationData
import com.nbs.shoppinglist.network.RetrofitClient
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location
    fun updateLocation(locationData: LocationData) {
        _location.value = locationData
    }

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address

    fun fetchAddress(latlng: String) {
        try {
            viewModelScope.launch {
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng,
                    Constants.mapAPIKey
                )
                _address.value = result.results
            }
        } catch (e: Exception) {
            Log.d(Constants.appTag, e.message.toString())
        }
    }

}