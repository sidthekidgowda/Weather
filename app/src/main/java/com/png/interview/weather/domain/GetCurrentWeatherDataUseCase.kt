package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.CurrentWeatherResponse
import javax.inject.Inject

interface GetCurrentWeatherDataUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<CurrentWeatherResponse, Unit>
}

class DefaultGetCurrentWeatherDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi
) : GetCurrentWeatherDataUseCase {
    override suspend fun invoke(query: String): NetworkResponse<CurrentWeatherResponse, Unit> {
        return weatherApi.getCurrentWeather(query)
    }
}