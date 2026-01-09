package org.example.project.weather.presentation.weatherInfo

import org.example.project.weather.domain.Location
import org.example.project.weather.domain.WeatherForeCast
import org.example.project.core.presentation.UiText

data class WeatherInfoState(

    val extendedSearchBar : Boolean = false,
    val errorMessage : UiText? = null,
    val searchQuery: String = "",
    val isLoading : Boolean = true,
    val weatherInfo: List<WeatherForeCast> = emptyList(),
    val favoriteLocations : List<Location> = emptyList(),
    val selectedTabIndex: Int = 0,
    val locationSheetOpened : Boolean = false

    )
