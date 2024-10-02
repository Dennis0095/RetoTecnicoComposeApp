package com.pe.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pe.designsystem.R

@Composable
fun TopBarComponent(
    title: String, onBackClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.purple_700))
    ) {

        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
            )
        }

        ActionButtonComponent(
            icon = R.drawable.ic_back,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            onBackClicked()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarComponentPreview(){
    TopBarComponent(
        title = "Configuración", onBackClicked = {}
    )
}