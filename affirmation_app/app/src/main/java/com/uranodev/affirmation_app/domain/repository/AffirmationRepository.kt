package com.uranodev.affirmation_app.domain.repository

import com.uranodev.affirmation_app.domain.models.Affirmation
import kotlinx.coroutines.flow.Flow

interface AffirmationRepository {
    fun getAffirmations(): Flow<List<Affirmation>>

    suspend fun createAffirmation(affirmation: Affirmation)
}