package com.uranodev.bussineescard.data.mocks

import androidx.compose.material.icons.Icons
import com.uranodev.bussineescard.R
import com.uranodev.bussineescard.ui.models.ContactModel

val contactOptions : List<ContactModel> = listOf(
    ContactModel(
        R.drawable.gmail_icon,
        linkToOpen = "mailto:cristiandramirezm.2024@gmail.com",
        tooltip = "Correo de gmail: cristiandramirezm.2024@gmail.com",
        description = "Correo de Gmail"
    ),
    ContactModel(
        R.drawable.linkedin_icon,
        linkToOpen = "https://www.linkedin.com/in/cristian-dev/",
        tooltip = "Linkedin",
        description = "Linkedin"
    ),
)