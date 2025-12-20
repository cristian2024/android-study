package com.uranodev.tip_calculator.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uranodev.tip_calculator.domain.models.Waiter
import com.uranodev.tip_calculator.domain.repositories.WaitersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitersViewModel @Inject constructor(repository: WaitersRepository) : ViewModel() {
    var waiters by mutableStateOf<List<Waiter>?>(null)
        private set

    init {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            waiters = repository.getWaiters();
        }
    }
}