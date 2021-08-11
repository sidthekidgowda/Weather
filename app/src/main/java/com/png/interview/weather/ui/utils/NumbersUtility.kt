package com.png.interview.weather.ui.utils

import java.lang.NumberFormatException

fun String.isNumber(): Boolean {
    return try {
        toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}