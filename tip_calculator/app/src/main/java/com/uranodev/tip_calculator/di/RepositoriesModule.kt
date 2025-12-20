package com.uranodev.tip_calculator.di

import com.uranodev.tip_calculator.domain.repositories.WaitersRepository
import com.uranodev.tip_calculator.infrastructure.repositories.WaiterFakeRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelMovieModule {
    @Binds
    abstract fun bindWaitersRepository(
        impl: WaiterFakeRepo
    ): WaitersRepository
}