package org.example.project.weather.data.Network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.data.safeCall
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.weather.data.dto.WeatherResponseDto


private val WEATHER_API = "8607f12126c2455591a81453250907"
private const val BASE_URL = "https://api.weatherapi.com/v1"

class KtorRemoteWeatherDataSource(
    private val httpClient: HttpClient //we will later on create an object factory to build the httpClient
): RemoteWeatherDataSource{
   override suspend fun searchWeather(
        query : String,
        resultLimit : Int?
    ): Result<WeatherResponseDto, DataError.Remote > {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/forecast.json"
            ){
                //specify some parameters
                parameter("q",query)
                parameter("days",7)
                parameter("key", WEATHER_API)
                parameter("limit",resultLimit)
                parameter("language","eng")
                //this specififes only the necessary fields we need from the API which can be similar to SearchedBookDto
                parameter("fields","location,current,forecast")
            }
        }
    }
}