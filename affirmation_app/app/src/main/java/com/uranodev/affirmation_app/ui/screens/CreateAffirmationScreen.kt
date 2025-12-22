package com.uranodev.affirmation_app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.uranodev.affirmation_app.domain.models.Affirmation
import com.uranodev.affirmation_app.ui.viewmodel.AffirmationEffect
import com.uranodev.affirmation_app.ui.viewmodel.AffirmationState
import com.uranodev.affirmation_app.ui.viewmodel.AffirmationViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CreateAffirmationScreen(
    modifier: Modifier = Modifier,
    onCloseScreen: () -> Unit,
    affirmationViewModel: AffirmationViewModel = hiltViewModel(),
) {
    var input by rememberSaveable { mutableStateOf("") }
    val state by affirmationViewModel.state.collectAsState()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    Scaffold(

        topBar = {
            IconButton(onClick = onCloseScreen) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    modifier = Modifier.size(48.dp),
                    contentDescription = ""
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Crea tu propia afirmación",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = input,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { newText ->
                    input = newText.trimStart { it == '0' }
                },
                label = {
                    Text(
                        "Afirmación",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            )
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val affirmation = Affirmation(input)

                    affirmationViewModel.createAffirmation(affirmation = affirmation)


                }, enabled = input.isNotEmpty()
            ) {
                Text("Crea tu afirmación")
            }
            Box(modifier = Modifier.size(48.dp)) { }
        }
    }

    when (state) {
        AffirmationState.Success -> onCloseScreen()
        else -> {}
    }

    LaunchedEffect(Unit) {
        affirmationViewModel.effect.collect { effect ->
            when (effect) {
                is AffirmationEffect.ShowErrorDialog -> {
                    errorMessage = effect.message
                }
            }
        }
    }
    if ((errorMessage ?: "").isNotEmpty()) {
        Dialog(onDismissRequest = {
            errorMessage = null
        }) {
            Card {
                Column() {
                    Text("Error")
                    Text(errorMessage!!)
                }

            }
        }
    }

}