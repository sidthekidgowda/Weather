package com.png.interview.weather.ui.binder

import com.png.interview.weather.ui.viewmodel.ForecastViewModel

class ForecastFragmentViewBinder(
    private val viewModel: ForecastViewModel,
    private val query: String
) {

    init {
        viewModel.retrieveForecast(query)
    }
}