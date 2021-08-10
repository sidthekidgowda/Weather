package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.AutcompleteResponseItem
import com.png.interview.weather.api.model.AutoCompleteResponse
import com.png.interview.weather.api.model.ForecastResponse
import javax.inject.Inject

interface GetAutocompleteListOfLocationsUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit>
}

class GetAutocompleteListOfLocationsUseCaseImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : GetAutocompleteListOfLocationsUseCase {
    override suspend fun invoke(query: String): NetworkResponse<List<AutcompleteResponseItem>, Unit> {
        return weatherApi.getAutocompleteResults(query)
    }
}