package com.uranodev.affirmation_app.ui.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.domain.repository.AffirmationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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

}