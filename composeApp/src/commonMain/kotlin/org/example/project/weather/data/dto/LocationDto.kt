package org.example.project.weather.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class LocationDto (

    @SerialName("name")val name : String,
    @SerialName("localtime")val time : String,

)