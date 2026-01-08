package org.example.project.weather.presentation.weatherInfo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherInfoViewModel : ViewModel() {

   private  val _state = MutableStateFlow(WeatherInfoState())
   val state : StateFlow<WeatherInfoState> = _state.asStateFlow()

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
         is WeatherInfoCommand.onCardClick -> {

         }
         is WeatherInfoCommand.onSearchBarValChange -> {

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
}