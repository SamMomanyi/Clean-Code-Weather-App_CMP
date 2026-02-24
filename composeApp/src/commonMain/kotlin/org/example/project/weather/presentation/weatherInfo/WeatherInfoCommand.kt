package org.example.project.weather.presentation.weatherInfo

sealed interface WeatherInfoCommand {

    // FIX: renamed to PascalCase — Kotlin objects/classes in sealed interfaces should be PascalCase
    data class OnSearchBarValChange(val queryState: SearchQueryInteractionState) : WeatherInfoCommand
    data class OnDaySelected(val selectedIndex: Int) : WeatherInfoCommand

    object AdjustSearchBar : WeatherInfoCommand
    object OpenLocationSheet : WeatherInfoCommand
}