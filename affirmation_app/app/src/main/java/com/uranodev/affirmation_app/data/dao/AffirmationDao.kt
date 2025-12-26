package com.uranodev.affirmation_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.uranodev.affirmation_app.data.entities.AffirmationEntity
import com.uranodev.affirmation_app.domain.models.Affirmation
import kotlinx.coroutines.flow.Flow

@Dao
interface AffirmationDao {
    @Query("Select * from Affirmations")
    fun getAll(): Flow<List<AffirmationEntity>>


    @Insert
    fun insertAffirmation(affirmation: AffirmationEntity)
}