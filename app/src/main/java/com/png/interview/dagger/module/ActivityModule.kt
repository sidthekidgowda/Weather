package com.png.interview.dagger.module

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.png.interview.MainActivity
import com.png.interview.dagger.viewmodel.InjectedViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module()
class ActivityModule(private val appCompatActivity: AppCompatActivity) {

    @Provides
    fun provideActivity(): Activity = appCompatActivity

    @Provides
    fun provideAppCompatActivity(): AppCompatActivity = appCompatActivity

    @Provides
    fun provideMainActivity(): MainActivity = appCompatActivity as MainActivity

    @Provides
    fun provideVerticalLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(appCompatActivity)

    @Provides
    fun provideLifecycleOwner(): LifecycleOwner = appCompatActivity

    @Provides
    fun provideFragmentManager(): FragmentManager = appCompatActivity.supportFragmentManager

    @Provides
    @ActivityViewModel
    fun provideViewModelProvider(viewModelFactory: ViewModelProvider.Factory): InjectedViewModelProvider = object :
        InjectedViewModelProvider {
        override fun <T : ViewModel> get(clazz: Class<T>): T {
            return ViewModelProviders.of(appCompatActivity, viewModelFactory)[clazz]
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ActivityViewModel
}