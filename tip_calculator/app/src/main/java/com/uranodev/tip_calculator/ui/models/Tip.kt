package com.uranodev.tip_calculator.ui.models

import com.uranodev.tip_calculator.domain.models.Waiter

data class Tip(
    val receiptValue: Double,
    val tipPercentage: Double = 0.0,
    val waiter: Waiter?,
    val tipValue: Double,
){
    val totalValue: Double
        get() = receiptValue + tipValue
}

fun Tip.updatePercentage(
    percentage: Double,
): Tip {
    return copy(
        tipPercentage = percentage,
        tipValue = percentage * receiptValue
    )
}

fun Tip.updateTipValue(
    tipValue: Double,
): Tip {
    val tipP = tipValue  / receiptValue
    return copy(
        tipPercentage = tipP,
        tipValue = tipValue,
    )
}

