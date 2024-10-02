package com.pe.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.R

@Composable
fun HeaderBottomSheet(title: String) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp,top = 21.dp)
    ) {

        Text(
            text = title,
            modifier = Modifier.align(Alignment.TopCenter),
            style = MaterialTheme.typography.labelMedium.copy(
                color = colorResource(id = R.color.white),
                fontSize = 16.sp
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewHeaderBottomSheet() {
        HeaderBottomSheet(title = "titulo")
}