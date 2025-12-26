package org.example.project.Weather.Presentation.WeatherInfo

import io.ktor.http.Url
import org.example.project.Weather.Domain.WeatherInfo

data class WeatherInfoState(

    val error : String? = null,
    val searchQuery: String = "" ,
    val isLoading : Boolean = false,
    val weatherInfo: WeatherInfo? = null,

)
