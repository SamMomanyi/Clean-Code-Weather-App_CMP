package org.example.project.weather.presentation.weatherInfo

import org.example.project.core.presentation.UiText
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.Location
import org.example.project.weather.domain.LocationAutoComplete
import org.example.project.weather.domain.WeatherInfo

data class WeatherInfoState(

    val extendedSearchBar: Boolean = false,
    val errorMessage: UiText? = null,
    val searchQueryInteractionState: SearchQueryInteractionState = SearchQueryInteractionState.Typed(""),
    val isLoading: Boolean = true,
    val selectedDay: Int = 0,
    val favoriteLocations: List<Location> = emptyList(),
    val selectedTabIndex: Int = 0,
    val locationSheetOpened: Boolean = false,
    val isCardClicked: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val todayList: List<HourlyForeCast> = emptyList(),
    val weeklyForecast: List<DailyForeCast> = emptyList(),
    //we can get the current day from the selected index
    val currentWeather: HourlyForeCast? = null,
    val startHour: Int? = null,
    val isDropDownMenuExpanded : Boolean = false,
    val autoCompleteData : LocationAutoComplete? = null

    )

sealed interface SearchQueryInteractionState {
    val query : String
    data class UsePreciseLocation(override val query: String) : SearchQueryInteractionState
    data class ModalSheetItem(override val query: String) : SearchQueryInteractionState
    //could later on switch to selected from the List
    data class Typed(override val query: String) : SearchQueryInteractionState
}