package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("maxtemp_c")val maxTemperature : Double,
    @SerialName("mintemp_c")val minTemperature : Double,
    @SerialName("code") val iconCode : Int,
    @SerialName("text")val text : String,
//    @SerialName("hour")val hourlyForeCastDto: HourlyForeCastDto
)
