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
import org.example.project.weather.domain.LocationAutoComplete
import org.example.project.weather.domain.PlaceRepository
import org.example.project.weather.domain.WeatherInfo
import org.example.project.weather.domain.WeatherRepository

class WeatherInfoViewModel(
    private val weatherRepository: WeatherRepository,
    private val placeRepository: PlaceRepository
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherInfoState(searchQueryInteractionState = SearchQueryInteractionState.Typed("Nairobi")))
    val state: StateFlow<WeatherInfoState> = _state.asStateFlow()

    private val cachedWeatherData: WeatherInfo? = null
    private var searchJob: Job? = null
    private var obtainCurrentLocation : Job? = null
    private var obtainAutoCompletes : Job ? = null

    init {
        observerSearchQuery()
    }


    fun WeatherCommandHandler(weatherInfoCommand: WeatherInfoCommand) {

        when (weatherInfoCommand) {

            WeatherInfoCommand.adjustSearchBar -> {
                if (state.value.extendedSearchBar) {
                    _state.update {
                        it.copy(
                            extendedSearchBar = false
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            extendedSearchBar = true
                        )
                    }
                }
            }

            is WeatherInfoCommand.onDaySelected -> {
                val clickedDay = weatherInfoCommand.selectedIndex
                _state.update {
                    it.copy(
                        selectedDay = clickedDay,
                    )
                }
            }

            is WeatherInfoCommand.onSearchBarValChange -> {
                //get the new query String from the command
                val newQuery = weatherInfoCommand.queryState.query

                when(weatherInfoCommand.queryState ){
                    is SearchQueryInteractionState.ModalSheetItem -> {
                        _state.update {
                            it.copy(
                                searchQueryInteractionState = weatherInfoCommand.queryState,
                                locationSheetOpened = false

                            )
                        }
                    }

                    is SearchQueryInteractionState.Typed -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                        obtainAutoComplete(weatherInfoCommand.queryState.query)
                        _state.update{
                            it.copy(
                                searchQueryInteractionState = weatherInfoCommand.queryState,
                                isLoading = false
                            )
                        }
                    }
                    is SearchQueryInteractionState.UsePreciseLocation -> {
                        obtainCurrentLocation().cancel()
                     obtainCurrentLocation = obtainCurrentLocation()

                    }
                }

            }

            WeatherInfoCommand.openLocationSheet -> {
                if (state.value.locationSheetOpened) {
                    _state.update {
                        it.copy(locationSheetOpened = false)
                    }
                } else {
                    _state.update {
                        it.copy(
                            locationSheetOpened = true
                        )
                    }
                }
            }

        }
    }


    private fun observerSearchQuery() {
        state
            .map {
                it.searchQueryInteractionState.query
            }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                             //   weatherInfo = cachedWeatherData
                            )
                        }
                    }
                    query.length >= 2 -> {

                        obtainAutoCompletes?.cancel()
                        obtainAutoCompletes = obtainAutoComplete(query)
                        //if we type a new searchJob we cancel the previous one
                        searchJob?.cancel()
                        //we call the searchWeather function , it will make use of WeatherRepository and it' interface
                        searchJob = searchWeatherData(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    //this makes sure the function returns a coroutine job
    private fun searchWeatherData(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        weatherRepository
            .searchWeather(query)
            .onSuccess { weatherData ->
                val startIndex = weatherData.currentWeatherData?.time?.parseToHourDigit()
                _state.update {
                    it.copy(
                        isLoading = false,
                        weatherInfo = weatherData,
                        //whenever we get data , the currentDay is always equal to the first day
                        currentWeather = weatherData.currentWeatherData,
                        //returns all the three days from 00->24
                        weeklyForecast = weatherData.dailyForecast,
                        //from the current time to the 23rd item.when it is first called
                        todayList = weatherData.hourlyForecast.subList(startIndex!!, (startIndex + 25)),
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

    private fun obtainCurrentLocation() = viewModelScope.launch{
        _state.update{
            it.copy(
                isLoading = true
            )
        }

        placeRepository.searchCurrentLocation()
            .onSuccess { preciseLocation ->
                _state.update{
                    it.copy(
                        searchQueryInteractionState = SearchQueryInteractionState.UsePreciseLocation(preciseLocation.cityName!!),
                                locationSheetOpened = false
                    )
                }
            }
            .onError { error->
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

    private fun obtainAutoComplete (query : String) = viewModelScope.launch{
        placeRepository
            .PlacesAutoComplete(query)
            .onSuccess { cityList ->
                _state.update {
                    it.copy(
                        autoCompleteData = cityList
                    )
                }
            }
            .onError {  error ->
                _state.update{
                    it.copy(
                       errorMessage = error.toUiText()
                        )
                }

            }
    }


    private fun String.parseToHourDigit(): Int {
        val isoFormat = this.replace(" ", "T")
        // 2. Parse to object and get hour
        return LocalDateTime.parse(isoFormat).hour
    }




}


