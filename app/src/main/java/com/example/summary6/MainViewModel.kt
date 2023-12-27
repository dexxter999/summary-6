package com.example.summary6

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _pinState = MutableStateFlow("")
    val pinState: StateFlow<String> = _pinState.asStateFlow()


}