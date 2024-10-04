package com.pe.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.presentation.R
import com.pe.presentation.util.maxCost
import com.pe.presentation.util.maxIncidents

@Composable
fun ItemDetailAppComponent(
    text: String,
    description: String
) {

    val style1 = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

    val style2 = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = colorResource(id = R.color.teal_700)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            textAlign = TextAlign.End, style = style1
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (text == "Costos operativos:") "S/ " + description else description,
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start,
                style = style2
            )
            if (description == "Obsoleto") {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(2.dp)
                        .background(colorResource(id = R.color.red))
                )
            } else if (text == "Número de incidentes:" && description.isNotEmpty()) {
                if (description.toInt() > maxIncidents) {
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(2.dp)
                            .background(colorResource(id = R.color.red))
                    )
                }
            } else if (text == "Usuarios activos:" && description.isNotEmpty()) {
                if (description.toInt() < maxIncidents) {
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(2.dp)
                            .background(colorResource(id = R.color.red))
                    )
                }
            } else if (text == "Costos operativos:" && description.isNotEmpty()) {
                if (description.toDouble() > maxCost) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(2.dp)
                            .background(colorResource(id = R.color.red))
                    )
                }
            }
        }

    }
}