package org.example.project.Weather.Domain

data class WeatherForeCast(
    val temperature : Double,
    val iconDescription : String,
    val time : String,
    val day : String,
    val location:String,
    val overallImageDescription : String,
    val precipitationImageDescription : String,
    val windImageDescription : String,
    val humidityImageDescription : String,

    )
