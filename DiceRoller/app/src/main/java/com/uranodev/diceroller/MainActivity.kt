package com.uranodev.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uranodev.diceroller.ui.theme.DiceRollerTheme
import com.uranodev.diceroller.ui.viewmodel.DiceRollViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: DiceRollViewModel by viewModels()

        enableEdgeToEdge()
        setContent {
            val state = viewModel.state.collectAsState().value
            DiceRollerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        Modifier.padding(innerPadding).fillMaxSize(),
                        Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Dice(state.actualDiceNumber.toInt())
                        RollButton(isRolling = state.isRolling, onClick = { viewModel.rollDice() })
                    }
                }
            }
        }
    }
}

@Composable
fun Dice(diceNumber: Int) {
    Image(
        painter = painterResource(id = getDiceDrawable(diceNumber)),
        contentDescription = "Dice in number $diceNumber"
    )
}

fun getDiceDrawable(diceNumber: Int): Int {
    return when (diceNumber) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1 // fallback
    }
}

@Composable
fun RollButton(modifier: Modifier = Modifier, isRolling: Boolean = false, onClick: () -> Unit) {
    Button(
        onClick,
        enabled = !isRolling
    ) {
        Text("Roll the dice")
    }

}