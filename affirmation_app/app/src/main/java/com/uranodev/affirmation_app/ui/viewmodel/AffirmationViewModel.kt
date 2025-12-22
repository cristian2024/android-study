package com.uranodev.affirmation_app.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.domain.repository.AffirmationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AffirmationViewModel @Inject constructor(val repository: AffirmationRepository) :
    ViewModel() {
    companion object {
        private const val MILLIS = 5_000L
    }


    val affirmations: StateFlow<List<Affirmation>> = repository.getAffirmations().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(MILLIS),
        initialValue = listOf()
    )

    private val _state = MutableStateFlow<AffirmationState>(
        AffirmationState.Ready
    )
    val state: StateFlow<AffirmationState> = _state

    private val _effect = MutableSharedFlow<AffirmationEffect>()
    val effect: SharedFlow<AffirmationEffect> = _effect


    fun createAffirmation(affirmation: Affirmation) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.createAffirmation(affirmation);
                _state.value = AffirmationState.Success
            } catch (e: Exception) {
                _state.value = AffirmationState.Ready
                _effect.emit(
                    AffirmationEffect.ShowErrorDialog(
                        "Se presento un error"
                    )
                )
            }

        }
    }

}