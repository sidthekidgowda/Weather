package com.png.interview

import android.app.Application
import android.content.Context
import com.png.interview.dagger.component.AppComponent
import com.png.interview.dagger.module.AppModule
import com.png.interview.dagger.component.DaggerAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import timber.log.Timber

@FlowPreview
@ExperimentalCoroutinesApi
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }

}