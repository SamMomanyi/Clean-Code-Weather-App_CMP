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
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun WeatherToday(

    currentWeather : HourlyForeCast,
    currentDay : DailyForeCast,
    currentDayForecast : List<HourlyForeCast>,
    weatherCommandHandler : (WeatherInfoCommand) -> Unit,
    state: WeatherInfoState

) {

    val imageRes = IconMapper(currentDay.overallIconDescription)
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val forecastDate = LocalDate.parse(currentDay.date)

    val dayName : String = if(forecastDate == today) "Now" else { "${currentDay.day },${currentDay.date}" }

    Card(
        enabled = false,
        shape = RoundedCornerShape(23.dp),
        colors = CardDefaults.cardColors(
            disabledContainerColor = LightBlue,
        ),
        elevation = CardDefaults.cardElevation(10.dp),
        border = BorderStroke(10.dp, color = Color.Transparent),
        onClick = {

        },
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
            ) {
                Text(
                    text = dayName
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${currentDay.highestTemperature} / ${currentDay.lowestTemperature}"
                    )

                    Icon(
                        painter = painterResource(imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(60.dp),
                        tint = Color.Unspecified
                    )

                    Text(
                        text = stringResource(DescriptionMapper(currentDay.overallIconDescription)),
                        textAlign = TextAlign.Right,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold
                    )
                }

              WeatherList(

                  modifier = Modifier.padding(10.dp),
                  dailyForecasts = null,
                  hourlyForecasts = currentDayForecast,
                  state = state,
                  currentWeather = currentWeather,
                  weatherCommandHandler = {weatherCommandHandler(it)},

              )

            }
        }
    }
}
