package com.uranodev.affirmation_app.di

import android.content.Context
import androidx.room.Room
import com.uranodev.affirmation_app.data.AffirmationDatabase
import com.uranodev.affirmation_app.data.dao.AffirmationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAffirmationDatabase(@ApplicationContext context: Context): AffirmationDatabase {
        return Room.databaseBuilder(
            context = context,
            AffirmationDatabase::class.java,
            "fruit_database"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideAffirmationDao(affirmationDatabase: AffirmationDatabase): AffirmationDao{
        return affirmationDatabase.affirmationDao()
    }



}