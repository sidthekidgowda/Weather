package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.databinding.FragmentSettingsBinding
import com.png.interview.ui.InjectedFragment

class SettingsFragment : InjectedFragment() {

    companion object {
        const val IMPERIAL_BUTTON = "imperial_button"
        const val METRIC_BUTTON = "metric_button"
    }

    private lateinit var settingsBinding: FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        return settingsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isImperialButtonSelected = sharedPreferences.getBoolean(IMPERIAL_BUTTON, false)
        val isMetricButtonSelected = sharedPreferences.getBoolean(METRIC_BUTTON, false)
        when {
            isImperialButtonSelected -> settingsBinding.imperialButton.isChecked = true
            isMetricButtonSelected -> settingsBinding.metricButton.isChecked = true
        }

        settingsBinding.imperialButton.setOnClickListener {
            sharedPreferences.edit().apply {
                putBoolean(METRIC_BUTTON, false)
                putBoolean(IMPERIAL_BUTTON, true)
                commit()
            }
        }
        settingsBinding.metricButton.setOnClickListener {
            sharedPreferences.edit().apply {
                putBoolean(METRIC_BUTTON, true)
                putBoolean(IMPERIAL_BUTTON, false)
                commit()
            }
        }
    }
}