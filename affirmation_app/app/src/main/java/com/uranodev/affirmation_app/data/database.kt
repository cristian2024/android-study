package com.uranodev.affirmation_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room


import androidx.room.RoomDatabase
import com.uranodev.affirmation_app.data.dao.AffirmationDao
import com.uranodev.affirmation_app.data.entities.AffirmationEntity

@Database(entities = [AffirmationEntity::class], version = 1, exportSchema = false)
abstract class AffirmationDatabase : RoomDatabase() {
    abstract fun affirmationDao(): AffirmationDao;
}