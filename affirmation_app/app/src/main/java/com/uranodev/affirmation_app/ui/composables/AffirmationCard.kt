package com.uranodev.affirmation_app.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.uranodev.affirmation_app.R
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.ui.others.mappedImages


@Composable
fun AffirmationCard(
    modifier: Modifier = Modifier,
    affirmation: Affirmation,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current
    val imageName = affirmation.iconName ?: "image_placeholder"
    val drawable = mappedImages[imageName] ?: R.drawable.image_placeholder

    Card() {
        Text(affirmation.message)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AffirmationCardPrev() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            Modifier.padding(innerPadding)
        ) {
            AffirmationCard(affirmation = Affirmation("quiero validar un par de detalles"))
        }

    }

}