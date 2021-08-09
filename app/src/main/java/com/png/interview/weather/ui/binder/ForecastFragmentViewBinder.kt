package com.png.interview.weather.ui.binder

import com.png.interview.weather.ui.viewmodel.ForecastViewModel

class ForecastFragmentViewBinder(
    private val viewModel: ForecastViewModel,
    private val query: String
) {

    val forecastViewData = viewModel.forecastData
    val isLoading = viewModel.isLoadingVisible
    val isError = viewModel.isErrorVisible

    init {
        viewModel.retrieveForecast(query)
    }
}