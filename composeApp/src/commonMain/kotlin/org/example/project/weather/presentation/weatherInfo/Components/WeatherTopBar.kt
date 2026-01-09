package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.DialogNavigator
import org.example.project.core.presentation.DesertWhite

import org.example.project.core.presentation.SandYellow
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState

@Composable
fun WeatherTopBar(
    state : WeatherInfoState,
    weatherInfoCommand: (WeatherInfoCommand) -> Unit,
    modifier : Modifier = Modifier
){
    //we want to hide the keyboard on imeSearch
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        Surface(
            tonalElevation = 6.dp,
            shadowElevation = 3.dp,
            shape = RoundedCornerShape(50),
            color = DesertWhite
        ) {
            AnimatedContent(
                targetState = state.extendedSearchBar
            ) {
                if (state.extendedSearchBar) {
                    WeatherSearchBar(
                        weatherInfoCommand,
                        searchQuery = state.searchQuery,
                        onSearchQueryChange = {
                            weatherInfoCommand(WeatherInfoCommand.onSearchBarValChange(it))
                        },
                        onImeSearch = { keyboardController?.hide() },
                        state = state
                    )
                } else {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .padding(10.dp)
                            .height(56.dp)
                    ) {
                        IconButton(
                            onClick = {
                                weatherInfoCommand(WeatherInfoCommand.adjustSearchBar)
                            },

                            ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = {
                                weatherInfoCommand(WeatherInfoCommand.openLocationSheet)
                            },

                            ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = {

                            },

                            ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                            )
                        }
                    }
                }
            }
        }
    }
}

