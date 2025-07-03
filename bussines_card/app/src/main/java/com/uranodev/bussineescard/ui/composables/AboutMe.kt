package com.uranodev.bussineescard.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AboutMe(aboutMe: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("About me", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.size(6.dp))
        Text(aboutMe, textAlign = TextAlign.Center)
    }
}