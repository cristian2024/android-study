package com.uranodev.jettimer.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Timer(progressValue: Float, from: Float, modifier: Modifier = Modifier) {
    val progress by animateFloatAsState(
        targetValue = progressValue,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
    )
    CircularProgressIndicator(
        progress = { progress },
    )
}