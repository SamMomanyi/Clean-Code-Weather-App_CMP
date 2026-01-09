package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.domain.DailyForecast
import org.example.project.weather.domain.WeatherForeCast

object CardProperties {
    enum class Card(val color: Color,val shape: Shape?,val cardSize: Dp,val cardDistance: Dp?) {
        DAYLYCARD(LightBlue, RoundedCornerShape(20.dp), 50.dp, cardDistance = 0.dp),
        HOURLYCARD(color = Color.Transparent,RoundedCornerShape(0.dp),70.dp, cardDistance = 10.dp)
    }
}