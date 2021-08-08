package com.png.interview.dagger.viewmodel

import androidx.lifecycle.ViewModel

interface InjectedViewModelProvider {
    operator fun <T : ViewModel> get(clazz: Class<T>): T
}