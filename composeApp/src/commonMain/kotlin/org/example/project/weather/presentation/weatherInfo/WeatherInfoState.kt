package org.example.project.weather.presentation.weatherInfo

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.example.project.core.presentation.UiText
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.Location
import org.example.project.weather.domain.WeatherInfo
import kotlinx.datetime.LocalDateTime

data class WeatherInfoState(

    val extendedSearchBar : Boolean = false,
    val errorMessage : UiText? = null,
    val searchQuery: String = "",
    val isLoading : Boolean = true,
    val selectedDay: Int = 0,
    val favoriteLocations : List<Location> = emptyList(),
    val selectedTabIndex: Int = 0,
    val locationSheetOpened : Boolean = false,
    val isCardClicked : Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val todayList : List<HourlyForeCast> = emptyList(),
    val weeklyForecast : List<DailyForeCast> = emptyList(),
    //we can get the current day from the selected index
    val currentWeather : HourlyForeCast? = null,
    val startHour : Int? = null,

    )
