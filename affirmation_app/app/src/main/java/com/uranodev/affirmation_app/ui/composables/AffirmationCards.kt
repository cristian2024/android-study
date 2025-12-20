package com.uranodev.affirmation_app.ui.composables

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uranodev.affirmation_app.domain.models.Affirmation

@Composable
fun AffirmationCards(
    modifier: Modifier = Modifier,
    affirmations: List<Affirmation>,
    onClickAffirmation: ((affirmation: Affirmation) -> Unit)? = null
) {
    Box(modifier.fillMaxSize()) {

        LazyColumn(
            Modifier.fillMaxSize()
        ) {
            items(affirmations.size) { index ->
                val affirmation = affirmations[index]
                AffirmationCard(affirmation = affirmation) {
                    onClickAffirmation?.invoke(affirmation)
                }
            }
        }

    }
}