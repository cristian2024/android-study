package com.uranodev.affirmation_app.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.uranodev.affirmation_app.domain.models.Affirmation

@Composable
fun CreateAffirmationDialog(
    modifier: Modifier = Modifier,
    onCreateAffirmation: (affirmation: Affirmation) -> Unit,
    onClose: () -> Unit,
) {
    var input by rememberSaveable { mutableStateOf("") }
    Dialog(
        onDismissRequest = {},
    ) {
        Card(modifier) {
            Column(
                Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterVertically,
                )
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
                            "Mensaje de la afirmación",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                )
                Column {
                    Button(
                        onClick = {
                            val affirmation = Affirmation(message = input)
                            onCreateAffirmation(affirmation)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = input.toIntOrNull() != null,
                    ) {
                        Text("Definir propina custom")
                    }
                    OutlinedButton(
                        onClick = onClose,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cancelar")
                    }
                }

            }
        }
    }
}