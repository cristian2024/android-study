package com.uranodev.tip_calculator.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uranodev.tip_calculator.domain.models.Waiter
import com.uranodev.tip_calculator.ui.viewModel.WaitersViewModel
import org.w3c.dom.Text

@Composable
fun SelectWaiter(
    modifier: Modifier = Modifier,
    selectedWaiter: Waiter?,
    onChangeWaiter: (waiter: Waiter) -> Unit,
) {
    val waiterViewModel: WaitersViewModel = viewModel()
    val waiters = waiterViewModel.waiters;
    val haveWaiters = (waiters ?: listOf()).isNotEmpty()

    var selectWaiterDialog by remember { mutableStateOf(false) }


    ElevatedButton(
        onClick = {
            if (haveWaiters) {
                selectWaiterDialog = true
            }
        },
        modifier,
    ) {
        if (!haveWaiters) {
            CircularProgressIndicator()
        } else {
            if (selectedWaiter != null) Text(selectedWaiter.name)
            else Text("Selecciona un Mesero")
        }

    }

    when {
        selectWaiterDialog -> {
            Dialog(
                onDismissRequest = {
                    selectWaiterDialog = false
                }) {
                Card {
                    Column(
                        Modifier.padding(all = 8.dp), verticalArrangement = Arrangement.spacedBy(
                            8.dp, alignment = Alignment.CenterVertically
                        )
                    ) {
                        waiters?.map { waiter ->
                            Card(
                                onClick = {
                                    onChangeWaiter(waiter)
                                    selectWaiterDialog = false
                                }
                            ) {
                                Row {
                                    Text("Meser@:")
                                    Text(waiter.name)
                                }
                            }
                        }

                    }
                }
            }
        }
    }

}