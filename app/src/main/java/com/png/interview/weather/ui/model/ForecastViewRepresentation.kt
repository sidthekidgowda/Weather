package com.png.interview.weather.ui.model

sealed class ForecastViewRepresentation {
    class ForecastViewRep(val forecast: List<ForecastViewData>) : ForecastViewRepresentation()
    object Loading : ForecastViewRepresentation()
    object Error : ForecastViewRepresentation()
}