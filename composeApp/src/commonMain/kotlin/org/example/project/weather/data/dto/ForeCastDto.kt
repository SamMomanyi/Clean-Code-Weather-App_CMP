package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForeCastDto(

    @SerialName("day")val day : DayDto,
    @SerialName("date")val date : String

)
