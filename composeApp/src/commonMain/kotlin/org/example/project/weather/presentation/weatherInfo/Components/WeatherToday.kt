package org.example.project.weather.presentation.weatherInfo.Components

import DescriptionMapper
import IconMapper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.domain.DailyForecast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.WeatherInfo
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState
import org.jetbrains.compose.resources.painterResource


@Composable
fun WeatherToday(
    dailyForecast : DailyForecast,
    todaysForecast : List<HourlyForeCast>,
    onCardClick : (Int) -> Unit,
    state: WeatherInfoState
) {

    val imageRes = IconMapper(dailyForecast.overallIconDescription)

    Card(
        enabled = false,
        shape = RoundedCornerShape(23.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        border = BorderStroke(10.dp, color = Color.Transparent),
        onClick = {

        },
    ) {
        Column() {
            Column(
            ) {
                Text(
                    text = "${dailyForecast.day}, ${dailyForecast.date}"
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${dailyForecast.highestTemperature} / ${dailyForecast.highestTemperature}"
                    )
                    Icon(
                        painter = painterResource(imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "${DescriptionMapper(dailyForecast.overallIconDescription)}",
                        textAlign = TextAlign.Right,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )

                }

              WeatherList(
                  modifier = Modifier.padding(10.dp),
                  onCardCLick = onCardClick,
                  dailyForecasts = null,
                  hourlyForecasts = todaysForecast,
                  state = state
              )

            }
        }
    }
}
