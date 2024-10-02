package com.pe.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.R

@Composable
fun ItemComponent(
    description: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = rememberRipple(true),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClicked()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 36.dp,
                    end = 36.dp,
                    top = 18.dp,
                    bottom = 18.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.headlineSmall.copy(color = colorResource(id = R.color.white), fontSize = 16.sp)
            )
        }
    }
}