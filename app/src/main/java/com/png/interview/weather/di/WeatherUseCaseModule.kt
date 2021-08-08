package com.png.interview.weather.di

import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.domain.DefaultCreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.domain.DefaultGetCurrentWeatherDataUseCase
import com.png.interview.weather.domain.GetCurrentWeatherDataUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase
}