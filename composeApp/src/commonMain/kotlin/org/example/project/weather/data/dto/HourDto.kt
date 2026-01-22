package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//each hour of a day
@Serializable
data class HourDto(

    @SerialName("time")val time : String,
    @SerialName("temp_c") val temp : Double,
    @SerialName("condition")val condition : ConditionDto,
    @SerialName("wind_kph")val windSpeed : Double,
    @SerialName("wind_dir")val windDirection : String,
    @SerialName("humidity")val humidity : Double,
    @SerialName("precip_mm")val precipitation : Double,

)