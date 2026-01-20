package org.example.project.weather.presentation.weatherInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.presentation.weatherInfo.Components.WeatherList
import org.example.project.weather.presentation.weatherInfo.Components.WeatherModalBottomSheet
import org.example.project.weather.presentation.weatherInfo.Components.WeatherToday
import org.example.project.weather.presentation.weatherInfo.Components.WeatherTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun WeatherInfoScreenRoot(
    viewModel : WeatherInfoViewModel = viewModel{ WeatherInfoViewModel() },
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
    currentDay : DailyForeCast,
    todayList : List<HourlyForeCast>,
    weeklyForecast : List<DailyForeCast>,
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
            .background(color = Color.Black)
            .statusBarsPadding(),
        
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
            weatherCommandHandler = { onCommand(it) },
        )

        WeatherList(
            modifier = Modifier.fillMaxWidth(),
            weatherCommandHandler = { onCommand(it) },
            dailyForecasts = weeklyForecast,
            scrollState = dailySearchResultListState,
            state = state,
            hourlyForecasts = null
        )
        if(state.locationSheetOpened){
        WeatherModalBottomSheet(
            weatherInfoCommand = {onCommand(it)},
            modifier = Modifier.padding(10.dp)
        )
    }

}}