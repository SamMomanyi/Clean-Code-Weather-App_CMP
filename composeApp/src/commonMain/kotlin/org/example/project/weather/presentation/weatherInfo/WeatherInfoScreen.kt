package org.example.project.weather.presentation.weatherInfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.example.project.weather.presentation.weatherInfo.Components.WeatherList
import org.example.project.weather.presentation.weatherInfo.Components.WeatherModalBottomSheet
import org.example.project.weather.presentation.weatherInfo.Components.WeatherToday
import org.example.project.weather.presentation.weatherInfo.Components.WeatherTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WeatherInfoScreenRoot(
    viewModel: WeatherInfoViewModel
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    // Extra safety: Check bounds to prevent IndexOutOfBoundsException
//        if (weeklyForecast.isNotEmpty() && selectedIndex < weeklyForecast.size) {
//            val currentDay = weeklyForecast[selectedIndex]
//
//            // Calculate hourly range safely
//            val startHour = selectedIndex * 24
//            val endHour = (selectedIndex + 1) * 24
//            // Ensure we don't crash if the hourly list is shorter than expected
//            val todayList = if (weatherInfo.hourlyForecast.size >= endHour) {
//                weatherInfo.hourlyForecast.subList(startHour, endHour)
//            } else {
//                emptyList()
//            }

    WeatherInfoScreen(
        state = state,
        onCommand = { viewModel.WeatherCommandHandler(it) }
    )
}

@Composable
fun WeatherInfoScreen(
    state: WeatherInfoState,
    onCommand: (WeatherInfoCommand) -> Unit
) {
    //hide keyboard on imeSearch
    val keyboardController = LocalSoftwareKeyboardController.current
    //for animations when searching we need a lazylist state
    val hourlySearchResultListState = rememberLazyListState()
    val dailySearchResultListState = rememberLazyListState()
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val presentDay = if(today == Clock.System.todayIn(TimeZone.currentSystemDefault())){
        true
    } else {
        false
    }

// Dummy Hourly Data

    //animate to item 1
    LaunchedEffect(
        state.selectedDay
    ) {
        hourlySearchResultListState.animateScrollToItem(0)
        dailySearchResultListState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .statusBarsPadding(),

        ) {

        WeatherTopBar(
            state = state,
            weatherInfoCommand = {
                onCommand(it)
            }
        )

        when (state.isLoading) {
            true -> {
                Scaffold() { padding -> // Note: Scaffold usually provides padding you should use
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding) // Apply scaffold padding
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
            }

            false -> {
                when (state.weatherInfo != null) {
                    true -> {

                        WeatherToday(
                            currentWeather = state.currentWeather!!,
                            currentDay = state.weeklyForecast[state.selectedDay],
                            currentDayForecast = if(
                                state.selectedDay == 0
                            ){
                                state.todayList
                            } else {
                                state.weatherInfo.hourlyForecast.subList((state.selectedDay*24),(state.selectedDay+1)*24)

                            },
                            state = state,
                            weatherCommandHandler = { onCommand(it) },
                        )


                        WeatherList(
                            modifier = Modifier.fillMaxWidth(),
                            weatherCommandHandler = { onCommand(it) },
                            dailyForecasts = state.weeklyForecast,
                            currentWeather = null,
                            scrollState = dailySearchResultListState,
                            state = state,
                            hourlyForecasts = null
                        )
                    }

                    false -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = state.errorMessage!!.asString(), color = Color.Red)
                        }
                    }
                }
            }
        }





    }
    if (state.locationSheetOpened) {
        WeatherModalBottomSheet(
            weatherInfoCommand = { onCommand(it) },
            modifier = Modifier.padding(10.dp)
        )
    }
}
