package org.example.project.weather.domain

data class DailyForeCast(
    val day : String,
    val date :String,
    val overallIconDescription : String,
    val highestTemperature : Double,
    val lowestTemperature : Double
)
