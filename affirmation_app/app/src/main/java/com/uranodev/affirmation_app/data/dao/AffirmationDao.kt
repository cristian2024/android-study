package com.uranodev.affirmation_app.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.uranodev.affirmation_app.data.entities.AffirmationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AffirmationDao {
    @Query("Select * from Affirmations")
    fun getAll(): Flow<List<AffirmationEntity>>
}