package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.domain.DailyForecast
import org.example.project.weather.domain.WeatherForeCast

@Composable
fun WeatherToday(
    weatherForeCast: WeatherForeCast?,
    dailyForecast: DailyForecast?
){
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
    ){
        Box(){
            Column(){
                Row(){
                    Column() {
                        dailyForecast?.day?.let {
                            Text(
                                text = it
                            )
                        }
                        dailyForecast?.date?.let {
                            Text(
                                text = it
                            )
                        }
                        dailyForecast?.lowestTemperature?.let {
                            Text(
                                text = it
                            )
                        }
                        dailyForecast?.highestTemperature?.let{
                            Text(
                                text = it
                            )
                        }
                    }
                    dailyForecast?.overallIconDescription?.let{
                        Text(

                        )
                    }
                }
            }
        }
    }
}