package com.uranodev.bussineescard.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uranodev.bussineescard.ui.models.SkillModel

@Composable
fun SkillNotch(skill: SkillModel, modifier: Modifier = Modifier) {
    Box(modifier) {
        AssistChip(
            onClick = {},
            label = { Text(skill.name) }
        )
    }
}

@Preview
@Composable
private fun SkillNotchShow() {
    val skill = SkillModel("Flutter")
    SkillNotch(skill)
}