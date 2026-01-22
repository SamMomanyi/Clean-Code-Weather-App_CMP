package org.example.project.weather.domain

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result

interface WeatherRepository {
    suspend fun searchWeather(query : String) : Result<WeatherInfo, DataError.Remote>

}