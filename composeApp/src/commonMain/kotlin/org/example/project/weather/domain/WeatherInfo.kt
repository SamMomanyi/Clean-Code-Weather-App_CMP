package org.example.project.weather.domain

//should be of pure kotlin code , we can then covert using Coil



data class WeatherInfo(
    // The specific data for right now (or the next 24 hours)
    val hourlyForecast: List<HourlyForeCast>,
    // The summary for the week
    val dailyForecast: List<DailyForecast>,
    // The "Headline" image for the current moment
    val currentWeatherData: HourlyForeCast?
)
