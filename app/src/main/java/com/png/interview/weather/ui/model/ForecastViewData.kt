package com.png.interview.weather.ui.model

data class ForecastViewData(
    val date: String,
    val minTempF: String,
    val minTempC: String,
    val maxTempF: String,
    val maxTempC: String,
    val windSpeedMPH: String,
    val windSpeedKPH: String,
    val condition: String
)