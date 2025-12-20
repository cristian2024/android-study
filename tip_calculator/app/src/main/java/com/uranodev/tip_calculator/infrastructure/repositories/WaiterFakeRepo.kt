package com.uranodev.tip_calculator.infrastructure.repositories

import com.uranodev.tip_calculator.data.fakes.fakeWaiters
import com.uranodev.tip_calculator.domain.models.Waiter
import com.uranodev.tip_calculator.domain.repositories.WaitersRepository
import kotlinx.coroutines.delay
import javax.inject.Inject


class WaiterFakeRepo @Inject constructor(): WaitersRepository  {
    override suspend fun getWaiters(): List<Waiter> {
        delay(200)
        return fakeWaiters
    }
}