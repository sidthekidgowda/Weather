package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
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

    var input: String = ""

    fun refreshClicked() {
        viewModel.submitCurrentWeatherSearch(currentWeatherLocation())
    }

    fun seeForecastClicked() {
        forecastAction(currentWeatherLocation())
    }

    fun settingsClicked() {
        settingsAction()
    }

    // if back button is clicked from forecast, edit text is empty
    // and so is input. We cannot refresh or see forecast from input,
    // so use the current weather location
    private fun currentWeatherLocation(): String = if (input.isEmpty()) {
        availableWeatherViewData.value?.name ?: input
    } else {
        input
    }

    fun goClicked() {
        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            viewModel.submitCurrentWeatherSearch(input)
        }
    }
}