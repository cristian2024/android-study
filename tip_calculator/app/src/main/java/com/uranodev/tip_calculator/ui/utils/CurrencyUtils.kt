package com.uranodev.tip_calculator.ui.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.currencyFormat(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.getDefault())
    formatter.minimumFractionDigits = 0
    formatter.maximumFractionDigits = 2
    return formatter.format(this);
}