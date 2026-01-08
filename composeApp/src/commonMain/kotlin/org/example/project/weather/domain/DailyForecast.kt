package org.example.project.weather.domain

data class DailyForecast(
    val day : String,
    val date :String,
    val overallIconDescription : String,
    val highestTemperature : Double,
    val lowestTemperature : Double
)
