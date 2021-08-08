package com.png.interview.dagger.component

import com.png.interview.MainActivity
import com.png.interview.dagger.module.ActivityModule
import com.png.interview.dagger.scope.ActivityScope
import com.png.interview.dagger.module.FragmentModule
import com.png.interview.ui.InjectedActivity

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun createFragmentComponent(fragmentModule: FragmentModule): FragmentComponent

    fun inject(mainActivity: MainActivity)
    fun inject(injectedActivity: InjectedActivity)
}
