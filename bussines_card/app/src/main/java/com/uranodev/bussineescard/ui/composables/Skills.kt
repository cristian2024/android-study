package com.uranodev.bussineescard.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uranodev.bussineescard.ui.models.SkillModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Skills(skills: List<SkillModel>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        skills.map {
            SkillNotch(it, Modifier.padding(horizontal = 2.dp))
        }
    }
}