package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.ForecastResponse
import javax.inject.Inject

interface GetForecastDataUseCase {
    suspend operator fun invoke(query: String, days: Int): NetworkResponse<ForecastResponse, Unit>
}

class GetForecastDataUseCaseImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : GetForecastDataUseCase {
    override suspend fun invoke(query: String, days: Int): NetworkResponse<ForecastResponse, Unit> {
        return weatherApi.getForecast(query, days)
    }
}