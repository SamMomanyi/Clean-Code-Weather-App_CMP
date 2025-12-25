package org.example.project.Weather.Presentation.WeatherInfo

sealed interface WeatherInfoCommand {

    data class onSearchBarValChange(val Location:String) : WeatherInfoCommand
    data class onCardClick(val day : String) : WeatherInfoCommand

}