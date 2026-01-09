package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import iconMapper
import org.example.project.core.presentation.DarkBlue
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.domain.DailyForecast
import org.example.project.weather.domain.WeatherForeCast
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherInfoCard(
    daily: DailyForecast? = null,
    hourly: WeatherForeCast? = null,
    onClick: () -> Unit
) {
    // 1. Determine the "Mode" and "Style"
    val cardProperties = if (daily != null) CardProperties.Card.DAYLYCARD else CardProperties.Card.HOURLYCARD

    // 2. Map the data based on which object is provided
    val displayTime = daily?.day ?: hourly?.time ?: ""
    val displayTemp = if (daily != null) "${daily.highestTemperature.toInt()}° / ${daily.lowestTemperature.toInt()}°" else "${hourly?.temperature?.toInt()}°"
    val iconCode = daily?.overallIconDescription ?: hourly?.iconDescription ?: "0"

    // 3. Get the Icon Resource (Logic only)
    val iconRes = iconMapper(iconCode = iconCode, isDay = true) // You can pass real isDay logic later

    Card(
        modifier = Modifier.padding(cardProperties.cardDistance ?: 0.dp),
        colors = CardDefaults.cardColors(containerColor = cardProperties.color),
        shape = cardProperties.shape ?: RoundedCornerShape(0.dp)
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


