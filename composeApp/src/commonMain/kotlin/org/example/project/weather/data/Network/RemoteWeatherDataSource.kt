package org.example.project.weather.data.Network

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.weather.data.dto.WeatherResponseDto

interface RemoteWeatherDataSource {
    suspend fun searchWeather(
        query : String,
        resultLimit: Int? = null
    ): Result<WeatherResponseDto, DataError.Remote>
}