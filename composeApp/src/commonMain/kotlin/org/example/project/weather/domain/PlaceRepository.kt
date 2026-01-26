package org.example.project.weather.domain

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result

interface PlaceRepository {
    suspend fun searchCurrentLocation() : Result<City, DataError.Local>
    suspend fun PlacesAutoComplete(query : String) : Result<LocationAutoComplete,DataError.Remote>


}