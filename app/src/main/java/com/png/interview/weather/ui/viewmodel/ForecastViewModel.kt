package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateForecastFromQueryUseCase
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class ForecastViewModel @Inject constructor(
    private val createForecastFromQueryUseCase: CreateForecastFromQueryUseCase,
    @Named("forecast_days") private val forecastDays: Int
) : ViewModel() {

    private val _forecastViewRepresentation = MutableStateFlow<ForecastViewRepresentation>(ForecastViewRepresentation.Loading)

    fun retrieveForecast(query: String) {
        viewModelScope.launch {
            _forecastViewRepresentation.value = createForecastFromQueryUseCase(query, days = forecastDays)
        }
    }

    val forecastData = _forecastViewRepresentation
        .map { (it as? ForecastViewRepresentation.ForecastViewRep)?.forecast }
        .asLiveData()

    val isLoadingVisible = _forecastViewRepresentation
        .map { it is ForecastViewRepresentation.Loading }
        .asLiveData()

    val isErrorVisible = _forecastViewRepresentation
        .map { it is ForecastViewRepresentation.Error }
        .asLiveData()
}