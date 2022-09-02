package com.mpierucci.composecrash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val state = MutableLiveData(State(pageOne = false, pageTwo = false))

    fun updatePageOne() {
        viewModelScope.launch {
            state.value = state.value!!.copy(pageOne = true)
            delay(10)
            state.value = state.value!!.copy(pageOne = null)

        }
    }

    fun updatePageTwo() {
        viewModelScope.launch {
            state.value = state.value!!.copy(pageTwo = true)
            delay(5000)
            state.value = state.value!!.copy(pageTwo = null)
        }
    }
}

data class State(
    val pageOne: Boolean?,
    val pageTwo: Boolean?
)