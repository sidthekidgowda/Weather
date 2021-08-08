package com.png.interview.weather.api.model

data class CurrentWeatherResponse(
    val current: Current,
    val location: Location
)