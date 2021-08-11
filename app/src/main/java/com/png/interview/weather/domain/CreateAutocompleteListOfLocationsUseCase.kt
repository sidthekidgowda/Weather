package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AutocompleteListViewRepresentation
import com.png.interview.weather.ui.model.AutocompleteViewData
import javax.inject.Inject

interface CreateAutocompleteListOfLocationsUseCase {
    suspend operator fun invoke(query: String): AutocompleteListViewRepresentation
}

class CreateAutocompleteListOfLocationsUseCaseImpl @Inject constructor(
    private val getAutocompleteListOfLocationsUseCase: GetAutocompleteListOfLocationsUseCase
) : CreateAutocompleteListOfLocationsUseCase {
    override suspend fun invoke(query: String): AutocompleteListViewRepresentation {
        return when (val result = getAutocompleteListOfLocationsUseCase(query)) {
            is NetworkResponse.Success -> {
                if (result.body.isEmpty()) {
                    AutocompleteListViewRepresentation.Error
                } else {
                    AutocompleteListViewRepresentation.AutocompleteListViewRep(
                        result.body.map {
                            AutocompleteViewData(
                                name = it.name,
                                region = it.region,
                                country = it.country
                            )
                        }.take(5)
                    )
                }
            }
            else -> {
                AutocompleteListViewRepresentation.Error
            }
        }
    }
}