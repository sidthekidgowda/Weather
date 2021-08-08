package com.png.interview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.png.interview.App
import com.png.interview.dagger.component.ActivityComponent
import com.png.interview.dagger.module.ActivityModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
abstract class InjectedActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = App.get(this)
            .appComponent
            .provideActivityComponent(ActivityModule(this))
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun getActivityComponent() = activityComponent

    inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java].apply {
            if (this is LifecycleObserver) {
                lifecycle.removeObserver(this)
                lifecycle.addObserver(this)
            }
        }
    }
}