package com.uranodev.affirmation_app.infrastructure.repositories

import com.uranodev.affirmation_app.data.dao.AffirmationDao
import com.uranodev.affirmation_app.data.entities.getAffirmations
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.domain.repository.AffirmationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AffirmationDaoRepository @Inject constructor(val affirmationDao: AffirmationDao) :
    AffirmationRepository {
    override fun getAffirmations(): Flow<List<Affirmation>> {
        val affirmations = affirmationDao.getAll()
        return affirmations.map { affirmation -> affirmation.getAffirmations }
    }
}

