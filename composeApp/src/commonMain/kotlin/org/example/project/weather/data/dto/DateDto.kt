package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DateDto(
    @SerialName("date")val date : String

)
