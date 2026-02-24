package org.example.project.weather.presentation.weatherInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.example.project.core.domain.onError
import org.example.project.core.domain.onSuccess
import org.example.project.core.presentation.toUiText
import org.example.project.weather.domain.PlaceRepository
import org.example.project.weather.domain.WeatherRepository

class WeatherInfoViewModel(
    private val weatherRepository: WeatherRepository,
    private val placeRepository: PlaceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        WeatherInfoState(searchQueryInteractionState = SearchQueryInteractionState.Typed("Nairobi"))
    )
    val state: StateFlow<WeatherInfoState> = _state.asStateFlow()

    // FIX: removed dead `cachedWeatherData` val that was never updated or used
    private var searchJob: Job? = null
    private var obtainCurrentLocationJob: Job? = null
    private var obtainAutoCompletesJob: Job? = null

    init {
        observeSearchQuery()
    }

    // FIX: renamed to camelCase per Kotlin conventions
    fun weatherCommandHandler(weatherInfoCommand: WeatherInfoCommand) {
        when (weatherInfoCommand) {

            WeatherInfoCommand.AdjustSearchBar -> {
                // FIX: simplified toggle with `!`
                _state.update { it.copy(extendedSearchBar = !it.extendedSearchBar) }
            }

            is WeatherInfoCommand.OnDaySelected -> {
                _state.update { it.copy(selectedDay = weatherInfoCommand.selectedIndex) }
            }

            is WeatherInfoCommand.OnSearchBarValChange -> {
                when (weatherInfoCommand.queryState) {
                    is SearchQueryInteractionState.ModalSheetItem -> {
                        _state.update {
                            it.copy(
                                searchQueryInteractionState = weatherInfoCommand.queryState,
                                locationSheetOpened = false
                            )
                        }
                    }

                    is SearchQueryInteractionState.Typed -> {
                        // FIX: don't set isLoading here — the coroutine hasn't run yet.
                        // Don't call obtainAutoComplete here either; observeSearchQuery handles it
                        // with debounce to avoid double-firing.
                        _state.update {
                            it.copy(searchQueryInteractionState = weatherInfoCommand.queryState)
                        }
                    }

                    is SearchQueryInteractionState.UsePreciseLocation -> {
                        // FIX: was `obtainCurrentLocation().cancel()` — created a job and
                        // immediately cancelled it. Should cancel the *previous* job.
                        obtainCurrentLocationJob?.cancel()
                        obtainCurrentLocationJob = obtainCurrentLocation()
                    }
                }
            }

            WeatherInfoCommand.OpenLocationSheet -> {
                // FIX: simplified toggle with `!`
                _state.update { it.copy(locationSheetOpened = !it.locationSheetOpened) }
            }
        }
    }

    // FIX: renamed to camelCase (was `observerSearchQuery`)
    private fun observeSearchQuery() {
        state
            .map { it.searchQueryInteractionState.query }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(errorMessage = null) }
                    }
                    query.length >= 2 -> {
                        obtainAutoCompletesJob?.cancel()
                        obtainAutoCompletesJob = obtainAutoComplete(query)

                        searchJob?.cancel()
                        searchJob = searchWeatherData(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchWeatherData(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        weatherRepository
            .searchWeather(query)
            .onSuccess { weatherData ->
                val startIndex = weatherData.currentWeatherData?.time?.parseToHourDigit()
                _state.update {
                    it.copy(
                        isLoading = false,
                        weatherInfo = weatherData,
                        currentWeather = weatherData.currentWeatherData,
                        weeklyForecast = weatherData.dailyForecast,
                        todayList = weatherData.hourlyForecast.subList(startIndex!!, startIndex + 25),
                        errorMessage = null
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        weatherInfo = null,
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }

    private fun obtainCurrentLocation() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }

        placeRepository.searchCurrentLocation()
            .onSuccess { preciseLocation ->
                _state.update {
                    it.copy(
                        searchQueryInteractionState = SearchQueryInteractionState.UsePreciseLocation(
                            preciseLocation.cityName!!
                        ),
                        locationSheetOpened = false
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        weatherInfo = null,
                        isLoading = false,
                        errorMessage = error.toUiText(),
                        locationSheetOpened = false
                    )
                }
            }
    }

    private fun obtainAutoComplete(query: String) = viewModelScope.launch {
        placeRepository
            .PlacesAutoComplete(query)
            .onSuccess { cityList ->
                _state.update { it.copy(autoCompleteData = cityList) }
            }
            .onError { error ->
                _state.update { it.copy(errorMessage = error.toUiText()) }
            }
    }

    private fun String.parseToHourDigit(): Int {
        val isoFormat = this.replace(" ", "T")
        return LocalDateTime.parse(isoFormat).hour
    }
}