package com.nbs.shoppinglist.models

data class LocationData(val lat: Double, val lng: Double)

data class GeocodingResponse(val status: String, val results: List<GeocodingResult>)

data class GeocodingResult(val formatted_address: String)