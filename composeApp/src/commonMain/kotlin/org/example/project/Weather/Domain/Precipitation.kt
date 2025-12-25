package org.example.project.Weather.Domain

import io.ktor.http.Url


data class Precipitation(
    val iconDescription: String,
    val time: String,
    val value: Double
)
