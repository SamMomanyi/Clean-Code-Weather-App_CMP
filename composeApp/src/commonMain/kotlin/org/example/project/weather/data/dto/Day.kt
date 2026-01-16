package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("maxtemp_c")val maxTemperature : Int,
    @SerialName("mintemp_c")val minTemperature : Int,
    @SerialName("code") val iconCode : Int,
    @SerialName("text")val text : String,
//    @SerialName("hour")val hourlyForeCastDto: HourlyForeCastDto
)
