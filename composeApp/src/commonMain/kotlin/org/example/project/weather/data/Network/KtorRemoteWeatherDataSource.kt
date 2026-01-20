package org.example.project.weather.data.Network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.data.safeCall
import org.example.project.core.domain.DataError
import org.example.project.weather.domain.WeatherInfo

private const val BASE_URL = ""

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) {
    suspend fun searchWeather(
        query : String,
        resultLimit : Int? = null
    ): Result<List<WeatherInfo>, DataError.Remote > {
        return safeCall {
            httpClient.get(
                urlString =
            ){
                //specify some parameters
                parameter("q",query)
                parameter("limit",resultLimit)
                parameter("language","eng")
                //this specififes only the necessary fields we need from the API which can be similar to SearchedBookDto
                parameter("fi")
            }
        }
    }
}