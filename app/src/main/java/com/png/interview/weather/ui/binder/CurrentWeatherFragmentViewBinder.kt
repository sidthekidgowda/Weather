package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.png.interview.weather.ui.utils.hideKeyboard
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (query: String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val autocompleteListData = viewModel.autocompleteListLiveData
    val isAutocompleteViewVisible = viewModel.isAutocompleteListVisible
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    val input = MutableLiveData("")

    private fun fetchCurrentWeather() {
        viewModel.setCurrentLocation(input.value!!)
        viewModel.submitCurrentWeatherSearch(input.value!!)
        input.value = ""
    }

    fun refreshClicked() {
        viewModel.submitCurrentWeatherSearch(viewModel.currentLocation())
    }

    fun seeForecastClicked() {
        forecastAction(viewModel.currentLocation())
        activity.hideKeyboard()
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        if (input.value!!.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.value!!.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG).show()
        } else {
            fetchCurrentWeather()
        }
    }
}