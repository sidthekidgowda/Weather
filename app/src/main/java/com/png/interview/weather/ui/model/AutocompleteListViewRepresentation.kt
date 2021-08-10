package com.png.interview.weather.ui.model

sealed class AutocompleteListViewRepresentation {
    class AutocompleteListViewRep(val autocompleteList: List<AutocompleteViewData>) : AutocompleteListViewRepresentation()
    object Empty : AutocompleteListViewRepresentation()
    object Error : AutocompleteListViewRepresentation()

}