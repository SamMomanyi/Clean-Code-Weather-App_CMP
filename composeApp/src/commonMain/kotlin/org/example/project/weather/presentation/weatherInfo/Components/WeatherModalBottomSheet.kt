package org.example.project.weather.presentation.weatherInfo.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmp_weatherapp.composeapp.generated.resources.Res
import cmp_weatherapp.composeapp.generated.resources.use_precise_location
import org.example.project.core.presentation.DarkBlue
import org.example.project.core.presentation.DesertWhite
import org.example.project.core.presentation.LightBlue
import org.example.project.weather.presentation.weatherInfo.WeatherInfoCommand
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherModalBottomSheet(
    weatherInfoCommand: (WeatherInfoCommand) -> Unit,
    modifier : Modifier
){
    val sheetState = rememberModalBottomSheetState {
        false
    }

    ModalBottomSheet(
        onDismissRequest = { weatherInfoCommand(WeatherInfoCommand.adjustSearchBar) },
        modifier = modifier,
        sheetState = sheetState,
        sheetMaxWidth = BottomSheetDefaults.SheetMaxWidth,
        shape = BottomSheetDefaults.ExpandedShape,
        containerColor = DesertWhite,
        contentColor = LightBlue,
        tonalElevation = 50.dp,
        scrimColor = BottomSheetDefaults.ScrimColor,
        properties = ModalBottomSheetDefaults.properties,
    ){
        BottomSheetPage(
            modifier = Modifier,
            weatherInfoCommand
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BottomSheetPage(
    modifier : Modifier,
    weatherInfoCommand: (WeatherInfoCommand) ->Unit
){
    val popularCities = listOf<String>(
        "Nairobi",
        "Kisii",
        "Mombasa",
        "HomaBay",
        "California",
        "Zanzibar",
        "Moscow",

    )
    Column(){
        FloatingActionButton(
            modifier = Modifier
                .padding(10.dp),
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            containerColor = DarkBlue,
            contentColor = DesertWhite,
        ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                tint = MaterialTheme.colorScheme.surfaceBright,
                contentDescription = null
            )
            Text(
                text = stringResource(Res.string.use_precise_location)
            )
        }
        HorizontalDivider()
        Text(
            text = "Popular"
        )
        FlowRow (
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            popularCities.forEach { city ->
                SuggestionChip(
                    onClick = {
                        weatherInfoCommand(WeatherInfoCommand.onSearchBarValChange(city))
                    },
                    label =  {
                       Text( city)
                    },
                    enabled = true,
                    shape = SuggestionChipDefaults.shape,
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = LightBlue,
                        contentColor = DesertWhite
                    ),
                    interactionSource = TODO()
                )
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
            text =  stringResource(Res.string.use_precise_location)
        )

        FloatingActionButton(
            modifier = Modifier
                .padding(10.dp),
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            containerColor = DarkBlue,
            contentColor = DesertWhite,
        ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                tint = MaterialTheme.colorScheme.surfaceBright,
                contentDescription = null
            )
            Text(
                text = stringResource(Res.string.use_precise_location)
            )
        }
    }
}