package org.example.project.weather.domain

sealed interface ForeCastData {
    data class  ForeCastDataA (val data : List<DailyForecast>) : ForeCastData
    data class ForeCastDataB  (val data : List<HourlyForeCast>) : ForeCastData
}