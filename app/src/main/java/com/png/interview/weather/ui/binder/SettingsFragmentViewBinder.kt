package com.png.interview.weather.ui.binder

import android.content.SharedPreferences
import com.png.interview.weather.ui.fragment.SettingsFragment

class SettingsFragmentViewBinder(private val sharedPreferences: SharedPreferences) {

    val isImperialButtonSelected =
        sharedPreferences.getBoolean(SettingsFragment.IMPERIAL_BUTTON, false)
    val isMetricButtonSelected =
        sharedPreferences.getBoolean(SettingsFragment.METRIC_BUTTON, false)

    fun onImperialButtonClicked() {
        sharedPreferences.edit().apply {
            putBoolean(SettingsFragment.METRIC_BUTTON, false)
            putBoolean(SettingsFragment.IMPERIAL_BUTTON, true)
            commit()
        }
    }

    fun onMetricButtonClicked() {
        sharedPreferences.edit().apply {
            putBoolean(SettingsFragment.METRIC_BUTTON, true)
            putBoolean(SettingsFragment.IMPERIAL_BUTTON, false)
            commit()
        }
    }
}