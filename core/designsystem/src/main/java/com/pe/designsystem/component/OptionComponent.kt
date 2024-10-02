package com.pe.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.R

@Composable
fun OptionComponent(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    name: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()


    val textColor = colorResource(id = R.color.white)

    val textStyle = if (pressed)
        MaterialTheme.typography.headlineMedium.copy(
            shadow = Shadow(
                colorResource(id = R.color.white).copy(alpha = 0.8f),
                offset = Offset(0f, 0f),
                blurRadius = 4f
            )
        )
    else MaterialTheme.typography.headlineMedium

    val buttonContent = colorResource(id = R.color.button_primary_on)
    Spacer(modifier = Modifier.height(12.dp))

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                onClicked()
            }
            .clip(RoundedCornerShape(30.dp))
            .background(buttonContent)
            .padding(12.dp)
    ) {

        Text(
            text = name,
            fontSize = 16.sp,
            color = textColor,
            style = textStyle,
        )
    }
}