package org.example.project.Weather.Presentation.WeatherInfo

import io.ktor.http.Url
import org.example.project.Weather.Domain.Location
import org.example.project.Weather.Domain.WeatherForeCast
import org.example.project.Weather.Domain.WeatherInfo
import org.example.project.core.presentation.UiText

data class WeatherInfoState(

    val errorMessage : UiText? = null,
    val searchQuery: String = "",
    val isLoading : Boolean = true,
    val weatherInfo: List<WeatherForeCast> = emptyList(),
    val favoriteLocations : List<Location> = emptyList(),
    val selectedTabIndex: Int = 0

    )
