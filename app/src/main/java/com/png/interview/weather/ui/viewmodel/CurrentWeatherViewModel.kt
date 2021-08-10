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

    private val _currentWeatherViewRepresentation =
        MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteListViewRepresentation =
        MutableStateFlow<AutocompleteListViewRepresentation>(AutocompleteListViewRepresentation.Empty)
    private val _currentLocation = MutableStateFlow("")
    private val _currentQuery = MutableStateFlow("")

    fun submitCurrentWeatherSearch(query: String) {
        _autocompleteListViewRepresentation.value = AutocompleteListViewRepresentation.Empty
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query)
        }
    }

    fun autoCompleteListSearch(query: String) {
        _currentQuery.value = query
        if (query.length <= 3) {
            _autocompleteListViewRepresentation.value = AutocompleteListViewRepresentation.Empty
            _currentWeatherViewRepresentation.value = CurrentWeatherViewRepresentation.Empty
        } else {
            _currentWeatherViewRepresentation.value = CurrentWeatherViewRepresentation.AutocompleteInProcess
            viewModelScope.launch {
                _autocompleteListViewRepresentation.value = createAutocompleteListOfLocationsUseCase(query)
            }
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

    val isAutocompleteListVisible: LiveData<Boolean> =
        _autocompleteListViewRepresentation
            .map { !(it is AutocompleteListViewRepresentation.Empty) }
            .asLiveData()

    val input = _currentQuery.asLiveData()
}