package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//when we call what will the api respond with
@Serializable
data class WeatherReponseDto(



    @SerialName("location")val location : LocationDto,
    @SerialName("current")val current : CurrentDto,
    @SerialName("forecast")val forecast : ForeCastRootDto? = null

)
