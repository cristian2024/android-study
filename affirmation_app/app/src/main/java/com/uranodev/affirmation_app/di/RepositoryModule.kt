package com.uranodev.affirmation_app.di

import com.uranodev.affirmation_app.data.dao.AffirmationDao
import com.uranodev.affirmation_app.domain.repository.AffirmationRepository
import com.uranodev.affirmation_app.infrastructure.repositories.AffirmationDaoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindAffirmationRepository(impl: AffirmationDaoRepository): AffirmationRepository
}