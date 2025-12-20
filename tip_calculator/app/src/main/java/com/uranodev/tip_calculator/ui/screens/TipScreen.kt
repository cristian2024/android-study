package com.uranodev.tip_calculator.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uranodev.tip_calculator.ui.composables.CustomTipDialog
import com.uranodev.tip_calculator.ui.composables.ModifyReceiptValue
import com.uranodev.tip_calculator.ui.composables.SelectWaiter
import com.uranodev.tip_calculator.ui.composables.TipCard
import com.uranodev.tip_calculator.ui.models.BasicTip
import com.uranodev.tip_calculator.ui.utils.currencyFormat
import com.uranodev.tip_calculator.ui.viewModel.TipViewModel

@Composable
fun TipScreen(modifier: Modifier = Modifier) {

    val initialValue = 20000.0;
    val extras = MutableCreationExtras().apply {
        set(TipViewModel.INITIAL_VALUE_KEY, initialValue)
    }

    val viewModel: TipViewModel = viewModel(
        factory = TipViewModel.Factory,
        extras = extras,
    )
    val tipInfo = viewModel.tipInfo;
    val receiptValue = tipInfo.receiptValue

    val customTip = tipInfo.tipValue
    // todo: usar mvvm
    val basicTipSelected = viewModel.basicTipSelected


    //dialogs management
    val customTipDialogOpen = remember { mutableStateOf(false) }
    var modifyReceiptDialog by remember { mutableStateOf(false) }
    var noTipDialogOpen by remember { mutableStateOf(false) }
    var successTipDialog by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.End
    ) {
        SelectWaiter(selectedWaiter = tipInfo.waiter, onChangeWaiter = viewModel::changeWaiter)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                24.dp,
                alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Total")
                Text(
                    receiptValue.currencyFormat(),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.clickable {
                        modifyReceiptDialog = true
                    })
                Text("Selecciona una propina")
            }
            Column(
                modifier.padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(
                    6.dp,
                    alignment = Alignment.CenterVertically,
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BasicTip.entries.map { tipValue ->
                        TipCard(
                            tipPercentage = tipValue.percentage,
                            totalValue = receiptValue,
                            modifier = modifier.weight(1f),
                            isSelected = tipValue == basicTipSelected
                        ) {
                            viewModel.changeTipType(tipValue);
                            viewModel.changeTipSelected(null)
                        }
                    }
                    if (viewModel.basicTipSelected == null) TipCard(
                        title = "Tip",
                        tipValue = customTip,
                        totalValue = receiptValue,
                        modifier = modifier.weight(1f),
                        isSelected = true
                    )
                }
                Column {
                    Button(
                        {
                            successTipDialog = true
                        },
                        modifier.fillMaxWidth(),
                    ) {
                        Text("Pagar")
                    }
                    Button(
                        {
                            customTipDialogOpen.value = true
                        },
                        modifier.fillMaxWidth(),
                        shape = ButtonDefaults.outlinedShape,
                        colors = ButtonDefaults.outlinedButtonColors()
                    ) {
                        Text("Propina custom")
                    }
                    Button(
                        {
                            noTipDialogOpen = true
                        },
                        modifier.fillMaxWidth(),
                        shape = ButtonDefaults.outlinedShape,
                        colors = ButtonDefaults.outlinedButtonColors()
                    ) {
                        Text("No propina")
                    }
                }

            }
            Box(Modifier.height(40.dp))

        }
    }
    when {
        // ...
        customTipDialogOpen.value -> {
            CustomTipDialog(onUpdateTip = { newTip ->
                viewModel.changeTipSelected(newTip)
                customTipDialogOpen.value = false
            }, onClose = {
                customTipDialogOpen.value = false
            })
        }

        modifyReceiptDialog -> {
            ModifyReceiptValue(onUpdateReceipt = { newReceiptValue ->
                viewModel.tipInfo = viewModel.tipInfo.copy(receiptValue = newReceiptValue)
                modifyReceiptDialog = false
            }, onClose = {
                modifyReceiptDialog = false
            })
        }

        successTipDialog -> {
            Dialog(
                onDismissRequest = {
                    successTipDialog = false
                }) {
                Card {
                    Column(
                        Modifier.padding(all = 8.dp), verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            alignment = Alignment.CenterVertically,
                        )
                    ) {
                        Text(
                            "Agradecemos tu ayuda!",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Tu valor total con propina fue de: ${tipInfo.tipValue.currencyFormat()}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )


                    }
                }
            }
        }

        noTipDialogOpen -> {
            Dialog(
                onDismissRequest = {
                    noTipDialogOpen = false
                }) {
                Card {
                    Column(
                        Modifier.padding(all = 8.dp), verticalArrangement = Arrangement.spacedBy(
                            8.dp,
                            alignment = Alignment.CenterVertically,
                        )
                    ) {
                        Text(
                            "Lo lamentamos mucho",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            "Esperamos mejorar nuestro servicio",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )


                    }
                }
            }
        }

    }
}
