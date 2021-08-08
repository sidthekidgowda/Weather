package com.png.interview.dagger.component

import android.content.SharedPreferences
import com.png.interview.App
import com.png.interview.dagger.module.ActivityModule
import com.png.interview.dagger.module.AppModule
import com.png.interview.dagger.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideActivityComponent(module: ActivityModule): ActivityComponent

    fun inject(app: App)

    fun providePreferences(): SharedPreferences
}
