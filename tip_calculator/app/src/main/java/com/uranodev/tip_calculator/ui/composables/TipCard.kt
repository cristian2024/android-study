package com.uranodev.tip_calculator.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uranodev.tip_calculator.ui.utils.currencyFormat
import java.text.NumberFormat
import java.util.Locale


/**
 * [tipPercentage]: numero de 0 a 1 representando el porcentaje del total que se va a dar como propina
 */
@Composable
fun TipCard(
    modifier: Modifier = Modifier,
    title: String? = null,
    tipValue: Double? = null,
    totalValue: Double,
    tipPercentage: Double = 0.0,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
        colors = if (isSelected) CardDefaults.cardColors() else CardDefaults.outlinedCardColors()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                6.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(
                text = title ?: "${(tipPercentage * 100).toInt()}%",
                fontSize = 24.sp,
            )
            Text(
                (tipValue ?: (totalValue * tipPercentage)).currencyFormat(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}