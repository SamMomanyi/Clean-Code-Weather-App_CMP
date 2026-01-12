package org.example.project.weather.presentation.weatherInfo

import org.example.project.weather.domain.DailyForecast

sealed interface WeatherInfoCommand {

    data class onSearchBarValChange(val Location:String) : WeatherInfoCommand
    data class onDaySelected(val selectedIndex : Int) : WeatherInfoCommand
    object adjustSearchBar : WeatherInfoCommand
    object openLocationSheet : WeatherInfoCommand
}