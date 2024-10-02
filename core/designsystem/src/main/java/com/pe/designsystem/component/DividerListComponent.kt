package com.pe.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pe.designsystem.R

@Composable
fun DividerListComponent(modifier: Modifier = Modifier) {

    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(colorResource(id = R.color.white).copy(0.25f)))
}
@Preview
@Composable
fun DividerListComponentPreview() {
    DividerListComponent()
}