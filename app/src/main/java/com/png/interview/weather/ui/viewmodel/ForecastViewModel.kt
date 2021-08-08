package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(

) : ViewModel() {

    fun retrieveForecast(query: String) {
        viewModelScope.launch {

        }
    }
}