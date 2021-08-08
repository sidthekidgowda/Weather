package com.png.interview.dagger.component

import com.png.interview.dagger.module.FragmentModule
import com.png.interview.dagger.scope.FragmentScope
import com.png.interview.ui.InjectedDialogFragment
import com.png.interview.ui.InjectedFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(injectedFragment: InjectedFragment)
    fun inject(injectedDialogFragment: InjectedDialogFragment)
}
