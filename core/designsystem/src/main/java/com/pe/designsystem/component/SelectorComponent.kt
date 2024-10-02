package com.pe.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.R
import com.pe.domain.entity.Selector

@Composable
fun SelectorComponent(
    modifier: Modifier = Modifier,
    header: String,
    selector: Selector?,
    onClicked: () -> Unit,
    isShowArrow: Boolean = false,
    isAccountSelected: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {

        Box {
            Text(
                text = header,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = colorResource(id = R.color.black),
                    fontSize = 16.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.teal_200),
                    RoundedCornerShape(12.dp)
                )
                .clickable(
                    indication = rememberRipple(true),
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClicked()
                }
                .height(42.dp),
            contentAlignment = Alignment.Center
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (isAccountSelected) {
                        Text(
                            text = selector?.description.orEmpty(),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = colorResource(id = R.color.white),
                                lineHeight = 12.sp,
                                fontSize = 16.sp
                            )
                        )
                    } else {
                        Text(
                            text = "Seleccione",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = colorResource(id = R.color.white),
                                lineHeight = 16.sp,
                                fontSize = 16.sp
                            )
                        )
                    }

                }

                if (isShowArrow) {
                    Image(painter = painterResource(id = R.drawable.ic_arrow_down),
                        contentDescription = "icon",
                        modifier = Modifier.clickable {
                            onClicked()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SelectorComponentPreview(){
    SelectorComponent(
        modifier = Modifier.padding(top = 20.dp),
        header = "Tipo de aplicación",
        selector = Selector(1, "Operacional"),
        onClicked = {
        },
        isShowArrow = true,
        isAccountSelected = false,
    )
}