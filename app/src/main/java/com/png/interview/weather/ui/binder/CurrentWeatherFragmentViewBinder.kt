package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.png.interview.weather.ui.utils.hideKeyboard
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: (query: String) -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val autocompleteListData = viewModel.autocompleteListLiveData
    val isAutocompleteViewVisible = viewModel.isAutocompleteListVisible
    val isAutocompleteListError = viewModel.isAutocompleteListError
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible
    val input = MutableLiveData("")

    init {
        // Reset to empty text if error is shown and remove autocomplete view
        viewModel.reset()
    }

    private fun fetchCurrentWeather() {
        viewModel.setCurrentLocation(input.value!!)
        viewModel.submitCurrentWeatherSearch(input.value!!)
        input.value = ""
    }

    fun refreshClicked() {
        viewModel.refresh()
    }

    fun seeForecastClicked() {
        forecastAction(viewModel.currentLocation())
        activity.hideKeyboard()
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun onAutocompleteLocationClicked(location: String) {
        input.value = ""
        viewModel.setCurrentLocation(location)
        viewModel.submitCurrentWeatherSearch(location)
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