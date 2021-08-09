package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.ForecastViewData
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import javax.inject.Inject

interface CreateForecastFromQueryUseCase {
    suspend operator fun invoke(query: String, days: Int): ForecastViewRepresentation
}

class CreateForecastFromQueryUseCaseImpl @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : CreateForecastFromQueryUseCase {
    override suspend fun invoke(query: String, days: Int): ForecastViewRepresentation {
        return when (val result = getForecastDataUseCase(query, days)) {
            is NetworkResponse.Success -> {
                ForecastViewRepresentation.ForecastViewRep(
                    result.body.forecast.forecastday.map {
                        ForecastViewData(
                            date = it.date,
                            minTempF = "${it.day.mintemp_f} F",
                            minTempC = "${it.day.mintemp_c} C",
                            maxTempF = "${it.day.maxtemp_f} F",
                            maxTempC = "${it.day.maxtemp_c} C",
                            windSpeedKPH = "${it.day.maxwind_kph} KPH",
                            windSpeedMPH = "${it.day.maxwind_mph} MPH",
                            condition = it.day.condition.text
                        )
                    }
                )
            }
            else -> {
                ForecastViewRepresentation.Error
            }
        }
    }
}