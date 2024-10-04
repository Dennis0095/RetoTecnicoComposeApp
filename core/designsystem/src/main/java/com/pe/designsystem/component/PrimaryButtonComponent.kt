package com.pe.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.R

@Composable
fun PrimaryButtonComponent(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    name: String,
    isEnabled: Boolean = true,
    showLoading: Boolean = false,
    colorBackground: Int = R.color.button_primary_on
) {
    val interactionSource = remember { MutableInteractionSource() }

    val textColor = if (isEnabled) colorResource(id = R.color.white)
    else colorResource(id = R.color.primary_btn_text_off)

    val textStyle = MaterialTheme.typography.headlineMedium.copy(
            shadow = Shadow(
                colorResource(id = R.color.white).copy(alpha = 0.8f),
                offset = Offset(0f, 0f),
                blurRadius = 4f
            ),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.height(40.dp)
            .clickable(
                enabled = isEnabled && !showLoading,
                indication = null,
                interactionSource = interactionSource
            ) {
                onClicked()
            }
            .clip(RoundedCornerShape(30.dp))
            .background(colorResource(id = colorBackground))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(start = 26.dp, end = 26.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showLoading) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.white),
                    modifier = Modifier.size(22.dp)
                )
            } else {

                Text(
                    text = name,
                    fontSize = 16.sp,
                    color = textColor,
                    style = textStyle,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPrimaryButtonComponent() {
    PrimaryButtonComponent(onClicked = {}, name = "Ingresar", modifier = Modifier.fillMaxWidth())
}