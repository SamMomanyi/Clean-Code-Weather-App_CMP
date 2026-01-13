package org.example.project.weather.presentation.weatherInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.weather.domain.DailyForecast
import org.example.project.weather.domain.Humidity
import org.example.project.weather.domain.Precipitation
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.Wind
import org.example.project.weather.presentation.weatherInfo.Components.WeatherList
import org.example.project.weather.presentation.weatherInfo.Components.WeatherToday
import org.example.project.weather.presentation.weatherInfo.Components.WeatherTopBar

@Composable
fun WeatherInfoScreenRoot(
    viewModel : WeatherInfoViewModel = WeatherInfoViewModel(),
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val selectedIndex = state.selectedDay
    val weekyForecast = state.weatherInfo?.dailyForecast
    val currentDay = state.weatherInfo?.dailyForecast[selectedIndex]
    val todayList = state.weatherInfo?.hourlyForecast?.subList(selectedIndex * 24,(selectedIndex + 1) * 24)

    WeatherInfoScreen(
        state = state,
        currentDay = currentDay!!,
        weeklyForecast = weekyForecast!!,
        todayList = todayList!!,
        onCommand = { viewModel.WeatherCommandHandler(it) }
    )
}

@Composable
fun WeatherInfoScreen(
    state :  WeatherInfoState,
    currentDay : DailyForecast,
    todayList : List<HourlyForeCast>,
    weeklyForecast : List<DailyForecast>,
    onCommand : (WeatherInfoCommand) -> Unit
){
    //hide keyboard on imeSearch
    val keyboardController = LocalSoftwareKeyboardController.current
    //for animations when searching we need a lazylist state
    val hourlySearchResultListState = rememberLazyListState()
    val dailySearchResultListState = rememberLazyListState()

// Dummy Hourly Data

    //animate to item 1
    LaunchedEffect(
        state.selectedDay
    ){
        hourlySearchResultListState.animateScrollToItem(0)
        dailySearchResultListState.animateScrollToItem(0)
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
            .statusBarsPadding()
    ){
        WeatherTopBar(
            state = state,
            weatherInfoCommand = {
                onCommand(it)
            }
        )
        WeatherToday(
            dailyForecast = currentDay,
            todaysForecast = todayList,
            state = state,
            onCardClick = { WeatherInfoCommand.onDaySelected(it) },
        )

        WeatherList(
            modifier = Modifier.fillMaxWidth(),
            onCardCLick = { WeatherInfoCommand.onDaySelected(it) },
            dailyForecasts = weeklyForecast,
            scrollState = dailySearchResultListState,
            state = state,
            hourlyForecasts = null
        )
    }

}