package com.uranodev.tip_calculator.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uranodev.tip_calculator.ui.models.BasicTip
import com.uranodev.tip_calculator.ui.models.Tip
import com.uranodev.tip_calculator.domain.models.Waiter
import com.uranodev.tip_calculator.ui.models.getBasicTip
import com.uranodev.tip_calculator.ui.models.updatePercentage
import com.uranodev.tip_calculator.ui.models.updateTipValue

class TipViewModel(
    private val initialValue: Double,
    private val initialTip: Double,
    private val waiter: Waiter?
) : ViewModel() {
    var tipInfo by mutableStateOf(
        Tip(
            receiptValue = initialValue,
            tipPercentage = initialTip,
            tipValue = initialTip * initialValue,
            waiter = waiter,
        )
    )



    val basicTipSelected: BasicTip?
        get() = tipInfo.tipPercentage.getBasicTip()


    fun changeWaiter(waiter: Waiter) {
        tipInfo = tipInfo.copy(waiter = waiter)
    }

    fun changeTipSelected(tipSelected: Double?) {
        if (tipSelected != null)
            tipInfo = tipInfo.updateTipValue(tipValue = tipSelected)
    }

    fun changeTipType(basicTip: BasicTip?) {
        if (basicTip != null) {
            tipInfo = tipInfo.updatePercentage(basicTip.percentage)
        }

    }


    // Define ViewModel factory in a companion object
    companion object {

        // Define a custom key for your dependency
        val INITIAL_VALUE_KEY = object : CreationExtras.Key<Double> {}
        val INITIAL_TIP_KEY = object : CreationExtras.Key<Double> {}
        val WAITER_KEY = object : CreationExtras.Key<Waiter?> {}
        private const val initialBasicTip = 0.2

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Get the dependency in your factory
                val inittialValue = this[INITIAL_VALUE_KEY] as Double
                val inittialTip = this[INITIAL_TIP_KEY] as Double?
                val initialWaiter = this[WAITER_KEY] as Waiter?

                TipViewModel(
                    inittialValue,
                    inittialTip ?: initialBasicTip,
                    initialWaiter,
                )
            }
        }
    }
}