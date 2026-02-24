package org.example.project.weather.data.repository

import dev.jordond.compass.Priority
import dev.jordond.compass.autocomplete.Autocomplete
import dev.jordond.compass.autocomplete.AutocompleteResult
import dev.jordond.compass.geocoder.MobileGeocoder
import dev.jordond.compass.geocoder.placeOrNull
import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult
import dev.jordond.compass.geolocation.mobile
import dev.jordond.compass.autocomplete.mobile
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.weather.data.toCity
import org.example.project.weather.domain.City
import org.example.project.weather.domain.LocationAutoComplete
import org.example.project.weather.domain.PlaceRepository

class DefaultPlaceRepository : PlaceRepository {

    override suspend fun searchCurrentLocation(): Result<City, DataError.Local> {
        val geoLocation = Geolocator.mobile().current(priority = Priority.HighAccuracy)

        return when (geoLocation) {
            is GeolocatorResult.Success -> {
                val coordinates = geoLocation.data.coordinates
                val place = MobileGeocoder().placeOrNull(coordinates)
                if (place != null) {
                    Result.Success(place.toCity())
                } else {
                    Result.Error(DataError.Local.UNKNOWN)
                }
            }

            is GeolocatorResult.Error -> when (geoLocation) {
                is GeolocatorResult.NotSupported -> Result.Error(DataError.Local.NOT_SUPPORTED)
                is GeolocatorResult.PermissionError -> Result.Error(DataError.Local.PERMISSION_ERROR)
                is GeolocatorResult.GeolocationFailed -> Result.Error(DataError.Local.GEOLOCATIONFAILED)
                is GeolocatorResult.NotFound -> Result.Error(DataError.Local.NOT_FOUND)
                else -> Result.Error(DataError.Local.UNKNOWN)
            }
        }
    }

    // FIX: renamed from `PlacesAutoComplete` to `placesAutoComplete` — method names are camelCase in Kotlin
    override suspend fun placesAutoComplete(query: String): Result<LocationAutoComplete, DataError.Remote> {
        val autocomplete = Autocomplete.mobile()
        val result = autocomplete.search(query)

        return when (result) {
            is AutocompleteResult.Success -> {
                val cities = result.data.map { it.toCity() }
                Result.Success(LocationAutoComplete(suggestions = cities))
            }
            is AutocompleteResult.Error -> {
                Result.Error(DataError.Remote.UNKNOWN_PLACE)
            }
        }
    }
}