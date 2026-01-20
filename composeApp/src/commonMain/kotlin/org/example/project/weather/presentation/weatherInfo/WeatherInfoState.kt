package org.example.project.weather.presentation.weatherInfo

import org.example.project.weather.domain.Location
import org.example.project.weather.domain.WeatherInfo

data class WeatherInfoState(

    val extendedSearchBar : Boolean = false,
    val errorMessage : String = "",
    val searchQuery: String = "",
    val isLoading : Boolean = true,
    val selectedDay: Int = 0,
    val favoriteLocations : List<Location> = emptyList(),
    val selectedTabIndex: Int = 0,
    val locationSheetOpened : Boolean = false,
    val isCardClicked : Boolean = false,
    val weatherInfo: WeatherInfo? = null

    )
