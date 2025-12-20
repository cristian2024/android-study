package com.uranodev.tip_calculator.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomTipDialog(
    onUpdateTip: (newTip: Double) -> Unit,
    onClose: () -> Unit,
) {
    var input by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = {},
    ) {
        Card {
            Column(
                Modifier.padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterVertically,
                )
            ) {
                Text(
                    "Propina custom",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = input,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { newText ->
                        input = newText.trimStart { it == '0' }
                    },
                    label = {
                        Text(
                            "Define tu nueva propina",
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                )
                Column {
                    Button(
                        onClick = {
                            onUpdateTip(input.toDouble())
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

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)

enum class Daypart {
    AFTERNOON, MORNING, EVENING, UNKNOWN
}

fun main() {
    val events = mutableListOf(
        Event(
            title = "Wake up",
            description = "Time to get up",
            daypart = Daypart.MORNING,
            durationInMinutes = 0
        ),
        Event(
            title = "Eat breakfast",
            daypart = Daypart.MORNING,
            durationInMinutes = 15,
        ),
        Event(
            title = "Learn about Kotlin",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 30,
        ),
        Event(
            title = "Practice Compose",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 60,
        ),
        Event(
            title = "Watch latest DevBytes video",
            daypart = Daypart.AFTERNOON,
            durationInMinutes = 10
        ),
        Event(
            title = "Check out latest Android Jetpack library",
            daypart = Daypart.EVENING,
            durationInMinutes = 45
        )
    )
    //eventos con duración menor a 60 minutos
    val shortEvents: List<Event> = events.filter { event ->
        event.durationInMinutes < 60
    }

    println("You have ${shortEvents.size} short events.")


    val eventsByDaypart: Map<Daypart, List<Event>> = events.groupBy {
        it.daypart
    }

    eventsByDaypart.forEach { (key, events) ->
        println("$key: = ${events.size} lengths")
    }
    events.last()
}