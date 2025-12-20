package com.uranodev.tip_calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uranodev.tip_calculator.ui.screens.TipScreen
import com.uranodev.tip_calculator.ui.viewModel.TipViewModel
import org.w3c.dom.Text

@Composable
fun MainComposable(modifier: Modifier = Modifier) {
    //todo: Crear una pantalla inicial para poner el valor de la cuenta
    val initialValue = 20000.0;
    val extras = MutableCreationExtras().apply {
        set(TipViewModel.INITIAL_VALUE_KEY, initialValue)
    }
    val viewModel: TipViewModel = viewModel(
        factory = TipViewModel.Factory,
        extras = extras,
    )
    //TODO: agregar navegación entre pantallas
    // Agregar pantalla para agregar valor de la cuenta
    //
    TipScreen()
}