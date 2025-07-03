package com.uranodev.bussineescard.ui.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.uranodev.bussineescard.ui.models.ContactModel
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Contact(contactModel: ContactModel, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val intent = remember {
        Intent(
            Intent.ACTION_VIEW,
            contactModel.linkToOpen?.toUri()
        )
    }
    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip { Text(contactModel.tooltip) }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = {
            context.startActivity(intent)
        }) {
            Icon(
                painter = painterResource(id = contactModel.iconId),
                contentDescription = contactModel.description,
            )
        }
    }
}