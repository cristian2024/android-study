package com.uranodev.bussineescard.ui.models

import androidx.compose.ui.graphics.vector.ImageVector

data class ContactModel(
    val iconId: Int,
    val linkToOpen: String?,
    val tooltip: String,
    val description: String,
)
