package com.pe.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pe.designsystem.R

@Composable
fun ActionButtonComponent(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    onClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .size(50.dp)
            .clickable(
                indication = rememberRipple(true),
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClicked()
            }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "icon back",
            tint = colorResource(id = R.color.white_half),
            modifier = Modifier
                .size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActionButtonComponentPreview(){
    ActionButtonComponent(
        icon = R.drawable.ic_back,
    ) {
    }
}
