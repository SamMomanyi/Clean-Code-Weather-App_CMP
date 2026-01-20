package org.example.project.weather.presentation.weatherInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.Failed
import org.example.project.core.domain.Response
import org.example.project.core.domain.Success
import org.example.project.core.domain.isLoading
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.Humidity
import org.example.project.weather.domain.Precipitation
import org.example.project.weather.domain.WeatherInfo
import org.example.project.weather.domain.Wind

class WeatherInfoViewModel : ViewModel() {

   private  val _state = MutableStateFlow(WeatherInfoState())
   val state : StateFlow<WeatherInfoState> = _state.asStateFlow()

    init {
        val dummyHours = (0 until 168).map { index ->
            val hourOfDay = index % 24
            HourlyForeCast(
                time = "${hourOfDay}:00",
                location = "Nairobi",
                temperature = 22.0 + (index % 5), // Varies slightly
                iconCode = if (hourOfDay in 6..18) "0" else "2", // Day vs Night
                wind = Wind(
                    iconDescription = "wind_speed",
                    time = "14:00",
                    windSpeed = 12.5 // km/h
                ),
                humidity = Humidity(
                    iconDescription = "humidity_level",
                    time = "14:00",
                    value = 60.0 // %
                ),
                precipitation = Precipitation(
                    iconDescription = "rain_chance",
                    time = "14:00",
                    value = 5.0 // %
                )
            )

        }

        val dummyDays = listOf(
            DailyForeCast("Mon", "Jan 12", "0", 28.5, 18.2),
            DailyForeCast("Tue", "Jan 13", "2", 26.1, 17.5),
            DailyForeCast("Wed", "Jan 14", "61", 22.0, 16.0), // Rain
            DailyForeCast("Thu", "Jan 15", "3", 24.5, 17.0), // Cloudy
            DailyForeCast("Fri", "Jan 16", "0", 29.0, 19.0),
            DailyForeCast("Sat", "Jan 17", "2", 27.2, 18.5),
            DailyForeCast("Sun", "Jan 18", "45", 21.0, 15.5) // Foggy
        )

        val dummyWeather = WeatherInfo(
            currentWeatherData = dummyHours.first(),
            hourlyForecast = dummyHours,
            dailyForecast = dummyDays
        )
        // 1. Generate the 168 hours and 7 days here
        // (Use the dummy logic from the previous step)

        // 2. Update the state
        _state.update {
            it.copy(
                isLoading = false,
                weatherInfo = dummyWeather,
                selectedDay = 0
            )
        }
    }






   fun WeatherCommandHandler(weatherInfoCommand: WeatherInfoCommand){

      when(weatherInfoCommand){

         WeatherInfoCommand.adjustSearchBar -> {
            if(state.value.extendedSearchBar){
               _state.update {
                  it.copy(
                     extendedSearchBar = false
                  )
               }
            }
            else{
               _state.update{
                  it.copy(
                     extendedSearchBar = true
                  )
               }
            }
         }

         is WeatherInfoCommand.onDaySelected -> {
             val clickedDay = weatherInfoCommand.selectedIndex
             _state.update{
                 it.copy(
                     selectedDay = clickedDay,
                 )
             }
         }

         is WeatherInfoCommand.onSearchBarValChange -> {
             _state.update {
                 it.copy(
                     searchQuery = weatherInfoCommand.Location
                 )
             }
         }

         WeatherInfoCommand.openLocationSheet -> {
            if(state.value.locationSheetOpened){
         _state.update {
            it.copy(locationSheetOpened = false)
         }
         }
            else {
               _state.update {
                  it.copy(
                     locationSheetOpened = true
                  )
               }
            }
         }

      }
   }

    private fun apiWeatherHandler(response : Response<WeatherInfo>) = viewModelScope.launch{
        when(response){
            is Success -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        selectedDay = 0
                    )
                }
            }
            is Failed -> {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = response.ex.toString()
                    )
                }
            }
            is isLoading -> {
                _state.update{
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }
    }


}