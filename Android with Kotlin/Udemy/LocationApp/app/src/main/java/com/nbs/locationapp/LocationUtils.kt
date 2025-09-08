package com.nbs.locationapp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

class LocationUtils() {

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(context: Context, viewModel: LocationViewModel) {

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let { location ->
                    viewModel.updateLocation(LocationData(location.latitude, location.longitude))
                }
            }
        }
        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000) // 10 seconds interval
                .build()

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun reverseGeocodeLocation(context: Context, locationData: LocationData): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        var coordinates = LatLng(locationData.lat, locationData.lng)
        val addresses: List<Address>? =
            geocoder.getFromLocation(
                coordinates.latitude,
                coordinates.longitude, 1
            )
        return if (addresses?.isNotEmpty() == true) addresses[0].getAddressLine(0)
        else "Address not found for $coordinates"
    }
}