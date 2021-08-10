package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.utils.hideKeyboard
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (query: String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    var input: String = viewModel.currentQuery()

    private fun fetchCurrentWeather() {
        viewModel.setCurrentQuery(input)
        viewModel.submitCurrentWeatherSearch(input)
    }

    fun refreshClicked() {
        viewModel.submitCurrentWeatherSearch(viewModel.currentQuery())
    }

    fun seeForecastClicked() {
        forecastAction(viewModel.currentQuery())
        activity.hideKeyboard()
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            fetchCurrentWeather()
        }
    }
}