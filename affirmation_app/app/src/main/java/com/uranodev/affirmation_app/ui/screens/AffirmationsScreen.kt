package com.uranodev.affirmation_app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.uranodev.affirmation_app.ui.composables.AffirmationCards
import com.uranodev.affirmation_app.ui.viewmodel.AffirmationViewModel

@Composable
fun AffirmationsScreen(
    modifier: Modifier = Modifier,
    affirmationViewModel: AffirmationViewModel = hiltViewModel(),
    onCreateNewAffirmation: () -> Unit,
) {
    val affirmations = affirmationViewModel.affirmations.collectAsState()

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(onClick = onCreateNewAffirmation) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) { innerPadding ->
        if (affirmations.value.isEmpty()) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "no afirmaciones",
                        modifier = Modifier.size(48.dp),
                    )
                    Text("No tienes afirmaciones")
                    Text("Crea una para empezar!", style = MaterialTheme.typography.titleLarge)
                }
            }
        } else {
            AffirmationCards(
                modifier.padding(innerPadding),
                affirmations.value
            )
        }


    }


}