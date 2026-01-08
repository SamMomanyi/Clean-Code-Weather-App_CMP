package org.example.project.weather.presentation.weatherInfo

sealed interface WeatherInfoCommand {

    data class onSearchBarValChange(val Location:String) : WeatherInfoCommand
    data class onCardClick(val day : String) : WeatherInfoCommand
    object adjustSearchBar : WeatherInfoCommand
    object openLocationSheet : WeatherInfoCommand
}