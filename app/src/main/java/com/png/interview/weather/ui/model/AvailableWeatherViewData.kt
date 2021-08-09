package com.png.interview.weather.ui.model

data class AvailableWeatherViewData(
    val name: String,
    val date: String,
    val temperatureF: String,
    val temperatureC: String,
    val condition: String,
    val windDirection: String,
    val windSpeedMPH: String,
    val windSpeedKPH: String
)