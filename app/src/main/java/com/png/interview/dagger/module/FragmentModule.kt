package com.png.interview.dagger.module

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.png.interview.dagger.viewmodel.InjectedViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
class FragmentModule(val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment = fragment

    @Provides
    fun providesDialogFragment(): DialogFragment = fragment as DialogFragment

    @Provides
    @ChildFragmentManager
    fun provideChildFragmentManager(): FragmentManager = fragment.childFragmentManager

    @Provides
    @FragmentViewModel(false)
    fun provideViewModelProvider(viewModelFactory: ViewModelProvider.Factory): InjectedViewModelProvider = object :
        InjectedViewModelProvider {
        override fun <T : ViewModel> get(clazz: Class<T>): T {
            return ViewModelProvider(fragment, viewModelFactory)[clazz].apply {
                if (this is LifecycleObserver) {
                    fragment.lifecycle.removeObserver(this)
                    fragment.lifecycle.addObserver(this)
                }
            }
        }
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChildFragmentManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FragmentViewModel(val parent: Boolean = false, val grandparent: Boolean = false)