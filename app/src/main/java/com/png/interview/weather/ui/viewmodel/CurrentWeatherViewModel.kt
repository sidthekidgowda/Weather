package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.*
import com.png.interview.weather.domain.CreateAutocompleteListOfLocationsUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutocompleteListViewRepresentation
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutocompleteListOfLocationsUseCase: CreateAutocompleteListOfLocationsUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteListViewRepresentation = MutableStateFlow<AutocompleteListViewRepresentation>(AutocompleteListViewRepresentation.Empty)
    private val _currentQuery = MutableStateFlow("")

    fun submitCurrentWeatherSearch(query: String) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query)
        }
    }

    fun autoCompleteListSearch(query: String) {
        viewModelScope.launch {
            _autocompleteListViewRepresentation.value = createAutocompleteListOfLocationsUseCase(query)
        }
    }

    fun currentQuery() = _currentQuery.value

    fun setCurrentQuery(query: String) {
        _currentQuery.value = query
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

    val isAutocompleteListVisible: LiveData<Boolean> = Transformations.map(autocompleteListLiveData) {
        it?.isNotEmpty() ?: false
    }
}