package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    @SerialName("text")val weatherDescription : String,
    @SerialName("code")val iconCode : Int
)

