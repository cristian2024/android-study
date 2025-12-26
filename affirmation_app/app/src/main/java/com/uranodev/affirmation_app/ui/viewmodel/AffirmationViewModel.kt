package com.uranodev.affirmation_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uranodev.affirmation_app.R
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.domain.repository.AffirmationRepository
import com.uranodev.affirmation_app.ui.others.mappedImages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

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
                //poniendo imagen aleatoria
                var newAffirmation = affirmation
                val newIndexImage = Random.nextInt(mappedImages.size + 1)

                if (affirmation.iconName == null) {
                    val image =
                        if (newIndexImage > mappedImages.size) "" else
                            mappedImages.keys.toList()[newIndexImage]
                    newAffirmation = affirmation.copy(iconName = image)
                }
                repository.createAffirmation(newAffirmation);
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