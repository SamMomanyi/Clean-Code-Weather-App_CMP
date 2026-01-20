package org.example.project.weather.domain

data class HourlyForeCast(
    val time: String,
    val temperature: Double,
    val iconCode : String,


    // These objects now carry their own specific values AND icons
    val wind: Wind,
    val humidity: Humidity,
    val precipitation: Precipitation
)
