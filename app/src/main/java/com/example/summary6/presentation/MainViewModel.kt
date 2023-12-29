package com.example.summary6.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {
    private val _pinState = MutableStateFlow("")
    val pinState get() = _pinState


    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.NumberClick -> onNumberClick(event.number)
            is MainEvent.DeleteClick -> onDeleteClick()
        }
    }

    private fun onNumberClick(number: Int) {
        _pinState.value += number.toString()
    }

    private fun onDeleteClick() {
        _pinState.value = _pinState.value.dropLast(1)
    }
}


sealed class MainEvent {
    data class NumberClick(val number: Int) : MainEvent()
    data object DeleteClick : MainEvent()
}