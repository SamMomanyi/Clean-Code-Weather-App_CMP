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
import org.example.project.weather.presentation.weatherInfo.Components.WeatherList
import org.example.project.weather.presentation.weatherInfo.Components.WeatherModalBottomSheet
import org.example.project.weather.presentation.weatherInfo.Components.WeatherToday
import org.example.project.weather.presentation.weatherInfo.Components.WeatherTopBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WeatherInfoScreenRoot(viewModel: WeatherInfoViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    WeatherInfoScreen(
        state = state,
        // FIX: call the correctly renamed camelCase handler
        onCommand = { viewModel.weatherCommandHandler(it) }
    )
}

@Composable
fun WeatherInfoScreen(
    state: WeatherInfoState,
    onCommand: (WeatherInfoCommand) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val hourlySearchResultListState = rememberLazyListState()
    val dailySearchResultListState = rememberLazyListState()

    // FIX: removed `presentDay` — it was always `true` (comparing today to itself) and never used

    LaunchedEffect(state.selectedDay) {
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
            weatherInfoCommand = { onCommand(it) }
        )

        when {
            state.isLoading -> {
                // FIX: removed unnecessary Scaffold wrapper here — it added extra padding
                // and a Scaffold inside a Column branch serves no purpose
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }

            state.weatherInfo != null -> {
                WeatherToday(
                    currentWeather = state.currentWeather!!,
                    currentDay = state.weeklyForecast[state.selectedDay],
                    currentDayForecast = if (state.selectedDay == 0) {
                        state.todayList
                    } else {
                        state.weatherInfo.hourlyForecast.subList(
                            state.selectedDay * 24,
                            (state.selectedDay + 1) * 24
                        )
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

            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.errorMessage?.asString() ?: "Something went wrong",
                        color = Color.Red
                    )
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