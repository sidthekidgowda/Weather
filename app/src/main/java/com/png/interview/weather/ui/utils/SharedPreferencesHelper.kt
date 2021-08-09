package com.png.interview.weather.ui

import android.content.SharedPreferences
import com.png.interview.weather.ui.fragment.SettingsFragment

/**
 * Default to imperial = true if both are false
 */
fun SharedPreferences.isImperial(): Boolean {
    val isImperial = getBoolean(SettingsFragment.IMPERIAL_BUTTON, false)
    val isMetric = getBoolean(SettingsFragment.METRIC_BUTTON, false)
    return if (isImperial) true else !isMetric
}
