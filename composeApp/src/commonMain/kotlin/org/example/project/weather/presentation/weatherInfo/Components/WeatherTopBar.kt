package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

import org.example.project.core.presentation.SandYellow
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState

@Composable

fun WeatherTopBar(
    state : WeatherInfoState,
    modifier : Modifier = Modifier
){
    var extension : Boolean
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ){
        Row(
            modifier  = modifier
        ){

    }
}
}