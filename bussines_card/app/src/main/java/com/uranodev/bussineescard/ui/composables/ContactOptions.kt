package com.uranodev.bussineescard.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uranodev.bussineescard.ui.models.ContactModel

@Composable
fun ContactOptions(contacts: List<ContactModel>, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
    ) {
        contacts.map { contact ->
            Contact(contact)
        }
    }
}