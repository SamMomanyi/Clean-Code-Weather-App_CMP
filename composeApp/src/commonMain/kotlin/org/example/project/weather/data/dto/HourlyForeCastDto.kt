package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyForeCastDto(
    //the json key

    @SerialName("key")val id: String,
    @SerialName("wind_kph")val wind : Int,
    @SerialName("wind_dir")val windDirection : String,
    @SerialName("humidity")val humidity : Int,
    @SerialName("precip_mm")val precipitation : Int,
    @SerialName("location")val location : LocationDto,
    @SerialName("current")val current : CurrentDto,

)
