package com.uranodev.tip_calculator.ui.models

enum class BasicTip(val percentage: Double) {
    TEN_PERCENT(0.1),
    FIFTEEN_PERCENT(0.15),
    TWENTY_PERCENT(0.2),
}

fun Double.getBasicTip(): BasicTip?{
    return when(this){
        0.2 -> BasicTip.TWENTY_PERCENT
        0.15 -> BasicTip.FIFTEEN_PERCENT
        0.1 -> BasicTip.TEN_PERCENT
        else -> null
    }
}