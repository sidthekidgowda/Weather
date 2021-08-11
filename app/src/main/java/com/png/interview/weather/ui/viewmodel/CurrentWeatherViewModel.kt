package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.*
import com.png.interview.weather.domain.CreateAutocompleteListOfLocationsUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutocompleteListViewRepresentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutocompleteListOfLocationsUseCase: CreateAutocompleteListOfLocationsUseCase
) : ViewModel() {

    companion object {
        private const val AUTOCOMPLETE_CHARACTER_MIN_LIMIT = 3
    }

    private val _currentWeatherViewRepresentation =
        MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteListViewRepresentation =
        MutableStateFlow<AutocompleteListViewRepresentation>(AutocompleteListViewRepresentation.Empty)
    private val _currentLocation = MutableStateFlow("")
    private val _currentQuery = MutableStateFlow("")

    fun submitCurrentWeatherSearch(query: String) {
        _autocompleteListViewRepresentation.value = AutocompleteListViewRepresentation.Empty
        fetchCurrentWeather(query)
    }

    fun refresh() {
        fetchCurrentWeather(currentLocation())
    }

    fun reset() {
        _currentWeatherViewRepresentation.value = calculateCurrentWeatherState()
        _autocompleteListViewRepresentation.value = AutocompleteListViewRepresentation.Empty
    }

    private fun fetchCurrentWeather(query: String) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query)
        }
    }

    fun autoCompleteListSearch(query: String) {
        // don't make duplicate network calls when back button is clicked
        if (query == _currentQuery.value && query.isNotEmpty()) return
        _currentQuery.value = query
        _currentWeatherViewRepresentation.value = calculateCurrentWeatherState()
        if (query.length < AUTOCOMPLETE_CHARACTER_MIN_LIMIT) {
            _autocompleteListViewRepresentation.value = AutocompleteListViewRepresentation.Empty
        } else {
            viewModelScope.launch {
                _autocompleteListViewRepresentation.value = createAutocompleteListOfLocationsUseCase(query)
            }
        }
    }

    private fun calculateCurrentWeatherState(): CurrentWeatherViewRepresentation {
        return if (_currentWeatherViewRepresentation.value is CurrentWeatherViewRepresentation.AvailableWeatherViewRep){
            _currentWeatherViewRepresentation.value
        } else if (_currentQuery.value.length >= 3) {
            CurrentWeatherViewRepresentation.AutocompleteInProcess
        } else {
            CurrentWeatherViewRepresentation.Empty
        }
    }

    fun currentLocation() = _currentLocation.value

    fun setCurrentLocation(query: String) {
        _currentLocation.value = query
    }

    val autocompleteListLiveData =
        _autocompleteListViewRepresentation
            .map { (it as? AutocompleteListViewRepresentation.AutocompleteListViewRep)?.autocompleteList }
            .asLiveData()

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val isErrorVisible = _currentWeatherViewRepresentation
        .map { it is CurrentWeatherViewRepresentation.Error }
        .asLiveData()

    val isAutocompleteListVisible =
        _autocompleteListViewRepresentation
            .map { !(it is AutocompleteListViewRepresentation.Empty) &&
                    _currentQuery.value.length >= AUTOCOMPLETE_CHARACTER_MIN_LIMIT }
            .asLiveData()

    val isAutocompleteListError =
        _autocompleteListViewRepresentation
            .map { it is AutocompleteListViewRepresentation.Error }
            .asLiveData()
}