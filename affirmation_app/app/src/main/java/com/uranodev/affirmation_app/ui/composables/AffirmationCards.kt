package com.uranodev.affirmation_app.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uranodev.affirmation_app.R
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.ui.others.mappedImages

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AffirmationCards(
    modifier: Modifier = Modifier,
    affirmations: List<Affirmation>,
    onClickAffirmation: ((affirmation: Affirmation) -> Unit)? = null
) {
    BoxWithConstraints(modifier.fillMaxSize()) {

        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { affirmations.count() },
            modifier = Modifier
                .width(maxWidth - 32.dp)

                .padding(top = 16.dp, bottom = 16.dp),
            preferredItemWidth = maxWidth,

            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val affirmation = affirmations[i]
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,

                ) {
                val image = mappedImages[affirmation.iconName] ?: R.drawable.image_placeholder
                Image(
                    modifier = Modifier
                        .maskClip(MaterialTheme.shapes.extraLarge).fillMaxWidth().background(color = Color.Green),
                    painter = painterResource(id = image),
                    contentDescription = "",

                    contentScale = ContentScale.FillWidth
                )

                Text(affirmation.message)
            }

        }

    }
}