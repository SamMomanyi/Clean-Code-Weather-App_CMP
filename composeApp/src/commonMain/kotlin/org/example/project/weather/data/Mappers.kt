package org.example.project.weather.data

import dev.jordond.compass.Place
import dev.jordond.compass.autocomplete.AutocompleteResult
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.example.project.weather.data.dto.CurrentDto
import org.example.project.weather.data.dto.ForeCastDayDto
import org.example.project.weather.data.dto.HourDto
import org.example.project.weather.data.dto.LocationDto
import org.example.project.weather.data.dto.WeatherResponseDto
import org.example.project.weather.domain.City
import org.example.project.weather.domain.DailyForeCast
import org.example.project.weather.domain.HourlyForeCast
import org.example.project.weather.domain.Humidity
import org.example.project.weather.domain.Location
import org.example.project.weather.domain.LocationAutoComplete
import org.example.project.weather.domain.Precipitation
import org.example.project.weather.domain.WeatherInfo
import org.example.project.weather.domain.Wind

fun WeatherResponseDto.toWeatherInfo() : WeatherInfo {


    return WeatherInfo(
        hourlyForecast = forecast?.forecastDays?.flatMap { it.hour }?.map { it.toHourlyForeCast() }?: emptyList(),
        dailyForecast = forecast?.forecastDays?.map{it.toDailyForeCast()} ?: emptyList(),
        currentWeatherData = current.toCurrent()
    )

}

fun CurrentDto.toCurrent() : HourlyForeCast{
    return HourlyForeCast(
        time = time,
        temperature = currentTemperature,
        iconCode = condition.iconCode.toString(),

        wind = Wind(
            time = time,
            windSpeed = wind,
            windDirection = windDirection
        ),

        humidity = Humidity(

            time = time,
            value = humidity

        ),
        precipitation = Precipitation(
            time = time,
            value = precipitation
        )
    )
}

fun LocationDto.toLocation()  : Location {
    return Location(
        name = name
    )
}

fun HourDto.toHourlyForeCast() : HourlyForeCast {

    return HourlyForeCast(
        time = time,
        temperature = temp,
        iconCode = condition.iconCode.toString(),

        wind = Wind(
            time = time,
            windSpeed = windSpeed,
            windDirection = windDirection
        ),

        humidity = Humidity(

            time = time,
            value = humidity

        ),
        precipitation = Precipitation(
            time = time,
            value = precipitation
        )
    )
}



fun ForeCastDayDto.toDailyForeCast()  : DailyForeCast {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val localDate = LocalDate.parse(date)
    val dayName = localDate.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
//    val displayDay = if(localDate == today) stringResource(Res.string.now) else dayName -> we will write this logic somewhere else inorder to make use of the string resource
    return DailyForeCast(
        day = dayName,
        date = date,
        overallIconDescription = day.condition.iconCode.toString(),
        highestTemperature = day.maxTemperature,
        lowestTemperature = day.minTemperature
    )

}

fun Place.toCity() : City {
    return City(
        cityName = locality ?: name ?: "Unknown location"
    )
}

// Mapper specifically for the Autocomplete results


fun AutocompleteResult.Success<Place>.toLocationAutoComplete() : LocationAutoComplete{
    return LocationAutoComplete(
        suggestions = data.map{it.toCity()}
    )
}