package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.png.interview.databinding.FragmentCurrentWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.CurrentWeatherFragmentViewBinder
import com.png.interview.weather.ui.isImperial

class CurrentWeatherFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCurrentWeatherBinding.inflate(inflater, container,false).apply {
            viewBinder = CurrentWeatherFragmentViewBinder(
                getViewModel(),
                requireActivity(),
                settingsAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSettingsFragment())
                },
                forecastAction = { query ->
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToForecastFragment(query))
                },
            )
            isImperial = sharedPreferences.isImperial()
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}