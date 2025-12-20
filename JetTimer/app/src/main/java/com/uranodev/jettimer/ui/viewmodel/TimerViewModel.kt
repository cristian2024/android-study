package com.uranodev.jettimer.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class TimerViewModel: ViewModel(){

    private val currentTime = MutableStateFlow<Float>(0.0F)
}