package com.png.interview.weather.ui.handler

import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

interface AutocompleteHandler {
    fun onAutocompleteLocationClicked(location: String)
}

class AutocompleteHandlerImpl(private val viewModel: CurrentWeatherViewModel) : AutocompleteHandler {

    override fun onAutocompleteLocationClicked(location: String) {
        viewModel.submitCurrentWeatherSearch(location)
    }
}