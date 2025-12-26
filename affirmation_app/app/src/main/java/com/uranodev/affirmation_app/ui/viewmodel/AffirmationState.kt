package com.uranodev.affirmation_app.ui.viewmodel

sealed interface AffirmationState {
    data object Ready: AffirmationState
    data object Success : AffirmationState
}

sealed interface AffirmationEffect {
    data class ShowErrorDialog(val message: String) : AffirmationEffect
}