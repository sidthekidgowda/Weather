package com.png.interview.weather.api.model

data class ForecastResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)