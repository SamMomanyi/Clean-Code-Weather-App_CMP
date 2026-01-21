package org.example.project.weather.data.repository

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.weather.data.Network.RemoteWeatherDataSource
import org.example.project.weather.data.toWeatherInfo
import org.example.project.weather.domain.WeatherInfo
import org.example.project.weather.domain.WeatherRepository


class DefaultWeatherRepository(
    private val remoteWeatherDataSource: RemoteWeatherDataSource
) : WeatherRepository {
  override  suspend fun searchWeather(query : String) : Result<WeatherInfo, DataError.Remote>{
        return remoteWeatherDataSource
            .searchWeather(query)
            //coming from inline fun in result that maps one result to another
            .map{ dto ->
                dto.toWeatherInfo()

            }
    }
}