package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState

@Composable
fun WeatherList(
    modifier : Modifier = Modifier,
    weatherCommandHandler: (WeatherInfoCommand) -> Unit,
    dailyForecasts: List<DailyForeCast>?,
    hourlyForecasts : List<HourlyForeCast>?,
    currentWeather : HourlyForeCast?,
    scrollState : LazyListState = rememberLazyListState(),
    state : WeatherInfoState
) {

    LazyRow(
        modifier = modifier,
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        if (dailyForecasts != null) {

            itemsIndexed(
                items = dailyForecasts,
                key = { index ,dailyForecast -> dailyForecast.day }
            ) { index,dailyForecast ->
                WeatherInfoCard(
                    daily = dailyForecast,
                    index = index,
                    onClick = { weatherCommandHandler(WeatherInfoCommand.onDaySelected(index)) },
                    isSelected = index == state.selectedDay,
                    state = state
                )
            }
        } else {
            //we will first use
            items(
                items =  hourlyForecasts!!,
                key = {it.time}
            ) { hourlyForecast ->
                WeatherInfoCard(
                    hourly = hourlyForecast,
                    isSelected = false,
                    state = state,
                    daily = null,
                    onClick = {null},
                )
            }
        }
    }
}

