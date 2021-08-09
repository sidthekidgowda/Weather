package com.png.interview.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.png.interview.App
import com.png.interview.dagger.component.FragmentComponent
import com.png.interview.dagger.module.FragmentModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
abstract class InjectedFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var fragmentComponent: FragmentComponent
    lateinit var sharedPreferences: SharedPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (context as InjectedActivity)
            .getActivityComponent()
            .createFragmentComponent(
                FragmentModule(this)
            )
        fragmentComponent.inject(this)
        sharedPreferences = (requireActivity().application as App)
            .appComponent
            .providePreferences()
    }

    inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this, viewModelFactory)[T::class.java].apply {
            if (this is LifecycleObserver) {
                lifecycle.removeObserver(this)
                lifecycle.addObserver(this)
            }
        }
    }
}