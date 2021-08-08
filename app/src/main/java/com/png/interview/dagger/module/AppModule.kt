package com.png.interview.dagger.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.preference.PreferenceManager
import com.png.interview.dagger.scope.ApplicationScope
import com.png.interview.weather.di.WeatherApiModule
import com.png.interview.weather.di.WeatherUseCaseModule

import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ViewModelModule::class,
        CommonApiModule::class,
        WeatherApiModule::class,
        WeatherUseCaseModule::class
    ]
)
class AppModule(val application: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application = application

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @ApplicationScope
    fun provideResources() = application.resources

    @Provides
    @ApplicationScope
    fun provideConnectivityManager(): ConnectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}