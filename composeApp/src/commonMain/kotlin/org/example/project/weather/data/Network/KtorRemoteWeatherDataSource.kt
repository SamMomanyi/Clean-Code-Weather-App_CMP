package org.example.project.weather.data.Network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.data.safeCall
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.weather.data.dto.WeatherResponseDto

// FIX: changed `val` to `const val` — compile-time constant, no runtime allocation.
// TODO: move WEATHER_API_KEY out of source code entirely. Add it to local.properties:
//   WEATHER_API_KEY=your_key_here
// Then read it via BuildConfig.WEATHER_API_KEY (add to build.gradle: buildConfigField(...))
// and make sure local.properties is in your .gitignore so the key is never committed.
private const val WEATHER_API_KEY = "8607f12126c2455591a81453250907"
private const val BASE_URL = "https://api.weatherapi.com/v1"

class KtorRemoteWeatherDataSource(
    private val httpClient: HttpClient
) : RemoteWeatherDataSource {

    override suspend fun searchWeather(
        query: String,
        resultLimit: Int?
    ): Result<WeatherResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get(urlString = "$BASE_URL/forecast.json") {
                parameter("q", query)
                parameter("days", 7)
                parameter("key", WEATHER_API_KEY)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter("fields", "location,current,forecast")
            }
        }
    }
}