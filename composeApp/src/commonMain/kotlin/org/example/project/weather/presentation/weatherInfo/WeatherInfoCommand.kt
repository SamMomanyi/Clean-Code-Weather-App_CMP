package org.example.project.weather.presentation.weatherInfo

sealed interface WeatherInfoCommand {

    data class onSearchBarValChange(val queryState : SearchQueryInteractionState ) : WeatherInfoCommand
    data class onDaySelected(val selectedIndex : Int) : WeatherInfoCommand



    object adjustSearchBar : WeatherInfoCommand
    object openLocationSheet : WeatherInfoCommand

}