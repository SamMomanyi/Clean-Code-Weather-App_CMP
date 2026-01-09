package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.weather.domain.DailyForecast

@Composable
fun WeatherDailyList(
    modifier : Modifier = Modifier,
    onCardCLick : (DailyForecast) -> Unit,
    dailyForecasts: List<DailyForecast>,
    scrollState : LazyListState = rememberLazyListState()
){
    LazyRow(
        modifier = modifier,
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ){
        items(
            items = dailyForecasts,
            key = {it.day}
        ){ dailyForecast ->
            WeatherInfoCard(
                daily = dailyForecast,
                onClick = {onCardCLick(dailyForecast)}
            )
        }
    }
}

