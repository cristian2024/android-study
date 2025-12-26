package com.uranodev.affirmation_app.domain.models

data class Affirmation(
    val message: String,
    val id: Int = 0,
    val iconName: String? = null,
)
