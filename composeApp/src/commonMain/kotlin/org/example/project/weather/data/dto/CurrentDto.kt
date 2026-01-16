package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentDto (
    @SerialName("key")val id: String,
    @SerialName("wind_kph")val wind : Int,
    @SerialName("wind_dir")val windDirection : String,
    @SerialName("humidity")val humidity : Int,
    @SerialName("precip_mm")val precipitation : Int,
    @SerialName("temp_c") val currentTemperature : Double,
    @SerialName("condition")val condition : ConditionDto
)