package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//each hour of a day
@Serializable
data class HourDto(

    @SerialName("localtime")val time : String,
    @SerialName("temp_c") val temp : Double,
    @SerialName("condition")val condition : ConditionDto,
    @SerialName("wind_kph")val windSpeed : Int,
    @SerialName("wind_dir")val windDirection : String,
    @SerialName("humidity")val humidity : Int,
    @SerialName("precip_mm")val precipitation : Int,

)