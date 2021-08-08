package com.png.interview.ui

import android.content.Context
import androidx.fragment.app.DialogFragment
import com.png.interview.dagger.component.FragmentComponent
import com.png.interview.dagger.module.FragmentModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
abstract class InjectedDialogFragment : DialogFragment() {

    lateinit var fragmentComponent: FragmentComponent

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = (requireActivity() as InjectedActivity)
            .getActivityComponent()
            .createFragmentComponent(
                FragmentModule(this)
            )
        fragmentComponent.inject(this)
    }
}