package com.mpierucci.composecrash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val state = MutableLiveData(false)

    fun update() {
        state.value = when(state.value) {
            false -> true
            true -> null
            null -> null
        }
    }
}