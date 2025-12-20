package com.uranodev.tip_calculator.domain.repositories

import com.uranodev.tip_calculator.domain.models.Waiter

interface WaitersRepository {
    suspend fun getWaiters(): List<Waiter>
}