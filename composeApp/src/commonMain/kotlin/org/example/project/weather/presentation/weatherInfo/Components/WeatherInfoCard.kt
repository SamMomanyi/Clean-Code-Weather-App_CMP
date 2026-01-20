package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import IconMapper
import androidx.compose.foundation.BorderStroke
import org.example.project.core.presentation.DarkBlue
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherInfoCard(
    daily: DailyForeCast? = null,
    index: Int? = null,
    hourly: HourlyForeCast? = null,
    isSelected: Boolean = false,
    state: WeatherInfoState?,
    onClick: (WeatherInfoCommand) -> Unit
) {
    // 1. Determine the "Mode" and "Style"
    val cardProperties = if (daily != null) CardProperties.Card.DAYLYCARD else CardProperties.Card.HOURLYCARD

    // 2. Map the data based on which object is provided
    val displayTime = daily?.day ?: hourly?.time ?: ""
    val displayTemp = if (daily != null) "${daily.highestTemperature.toInt()}° / ${daily.lowestTemperature.toInt()}°" else "${hourly?.temperature?.toInt()}°"
    val iconCode = daily?.overallIconDescription ?: hourly?.iconCode ?: "0"
    // Hourly cards shouldn't usually trigger the "selectedDay" border.
    val isCardSelected = daily != null && index != null && state?.selectedDay == index
    // 3. Get the Icon Resource (Logic only)
    val iconRes = IconMapper(iconCode = iconCode, isDay = true) // You can pass real isDay logic later

    Card(
        modifier = Modifier.padding(cardProperties.cardDistance ?: 0.dp),
        colors = CardDefaults.cardColors(containerColor = cardProperties.color),
        shape = cardProperties.shape ?: RoundedCornerShape(0.dp),
        border = if(isCardSelected) {
            BorderStroke(
                width = 4.dp,
                color = Color.White
            )
        } else {
            null
        },
        onClick = {
            index?.let {
                onClick(WeatherInfoCommand.onDaySelected(it))
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = displayTime, color = DarkBlue)

            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified // Keeps the original colors of your XML
            )

            Text(text = displayTemp, color = DarkBlue, fontWeight = FontWeight.Bold)
        }
    }
}


