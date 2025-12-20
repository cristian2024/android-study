package com.uranodev.diceroller.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

data class DiceUiState(
    val actualDiceNumber: Number = 1,
    val isRolling: Boolean = false
)

class DiceRollViewModel: ViewModel() {

    private val _state = MutableStateFlow(DiceUiState())
    val state: StateFlow<DiceUiState> = _state.asStateFlow()

    fun rollDice(rollTimeInMil: Int = 1000) {

        viewModelScope.launch {
            
            val delayTime: Int = 100
            val next = Random.nextInt(from = 1, until = 7)
            _state.update { state -> state.copy(
                isRolling = true,
                actualDiceNumber = next
            )}

            for(i in 0..rollTimeInMil step delayTime) {
                val next = Random.nextInt(from = 1, until = 7)
                delay(delayTime.toLong())
                _state.update { state -> state.copy(
                    actualDiceNumber = next
                )}

            }
            _state.update { state -> state.copy(
                isRolling = false,
            )}


        }
    }
}