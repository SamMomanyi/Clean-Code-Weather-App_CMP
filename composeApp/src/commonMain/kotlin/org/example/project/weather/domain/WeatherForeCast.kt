package org.example.project.weather.domain

data class WeatherForeCast(
    val time: String,
    val location: String,
    val temperature: Double,
    val iconDescription : String,


    // These objects now carry their own specific values AND icons
    val wind: Wind,
    val humidity: Humidity,
    val precipitation: Precipitation
)
