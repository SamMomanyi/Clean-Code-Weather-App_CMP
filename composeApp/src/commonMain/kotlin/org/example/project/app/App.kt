package org.example.project.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.compose_multiplatform
import io.ktor.client.engine.HttpClientEngine
import org.example.project.core.data.HttpClientFactory
import org.example.project.weather.data.Network.KtorRemoteWeatherDataSource
import org.example.project.weather.data.repository.DefaultWeatherRepository
import org.example.project.weather.presentation.weatherInfo.WeatherInfoScreen
import org.example.project.weather.presentation.weatherInfo.WeatherInfoScreenRoot
import org.example.project.weather.presentation.weatherInfo.WeatherInfoViewModel

@Composable
@Preview
//the engine is normally platform specific
fun App(engine : HttpClientEngine) {
    MaterialTheme {
        WeatherInfoScreenRoot(
            viewModel = remember {
                WeatherInfoViewModel(
                    weatherRepository = DefaultWeatherRepository(
                        remoteWeatherDataSource = KtorRemoteWeatherDataSource(
                        httpClient = HttpClientFactory.create(
                            engine
                        )
                    )
                ))
            }
        )
    }
}