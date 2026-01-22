package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentDto (

    @SerialName("last_updated")val time : String,
    @SerialName("wind_kph")val wind : Double,
    @SerialName("wind_dir")val windDirection : String,
    @SerialName("humidity")val humidity : Double,
    @SerialName("precip_mm")val precipitation : Double,
    @SerialName("temp_c") val currentTemperature : Double,
    @SerialName("condition")val condition : ConditionDto

)