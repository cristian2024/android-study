package com.uranodev.bussineescard.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uranodev.bussineescard.R
import com.uranodev.bussineescard.data.mocks.contactOptions
import com.uranodev.bussineescard.data.mocks.skills


@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    R.drawable.gmail_icon;
    Box(modifier) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BusinessHeader(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(18.dp))
            PersonalInformation(
                shownName = stringResource(R.string.dev_name),
                title = stringResource(R.string.dev_title),
                company = stringResource(R.string.dev_company),
            )
            Spacer(Modifier.size(12.dp))
            ContactOptions(contactOptions)
            Spacer(Modifier.size(12.dp))
            AboutMe("High skilled mobile developer with knowledge in multiplatform and native development")
            Spacer(Modifier.size(12.dp))
            Skills(skills)
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "BusinessCard",
)
@Composable
private fun ShowBusinessCard() {
    BusinessCard()
}