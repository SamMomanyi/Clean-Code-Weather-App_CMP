package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyForecastDto(
    @SerialName("day")val dailyResults : List<Day>,
    @SerialName("forecastDay")val forecastDay : List<forecastDay>
)
