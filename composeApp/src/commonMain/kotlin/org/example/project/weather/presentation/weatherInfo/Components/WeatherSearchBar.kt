package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.search_hint
import org.example.project.core.presentation.DarkBlue
import org.example.project.core.presentation.SandYellow
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.example.project.weather.presentation.weatherInfo.WeatherInfoState
import org.jetbrains.compose.resources.stringResource

@Composable
fun WeatherSearchBar(
    weatherCommand : (WeatherInfoCommand) -> Unit,
    searchQuery : String,
    onSearchQueryChange : (String) -> Unit,
    onImeSearch : () -> Unit, //trigger the search action when we click the search button on the phone
    state : WeatherInfoState
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            handleColor = SandYellow,
            backgroundColor = SandYellow
        )
    ) {
        AnimatedContent(
            targetState = state.extendedSearchBar
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),

                value = searchQuery,
                onValueChange = { onSearchQueryChange(it) },
                shape = RoundedCornerShape(100),
                colors = OutlinedTextFieldDefaults.colors(
                    cursorColor = DarkBlue,
                    focusedBorderColor = SandYellow
                ),
                placeholder = {
                    Text(
                        text = stringResource(Res.string.search_hint)
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(
                        visible = searchQuery.isNotBlank()
                    ) {
                        IconButton(
                            onClick = {
                                onSearchQueryChange("")
                                weatherCommand(WeatherInfoCommand.adjustSearchBar)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                tint = DarkBlue
                            )
                        }
                    }
                },
                singleLine = true,
                //show keyboard
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onImeSearch()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                )
            )
        }
    }
}