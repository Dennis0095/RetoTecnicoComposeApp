package com.pe.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pe.designsystem.component.PrimaryButtonComponent
import com.pe.presentation.R

@Composable
fun WarningOptionsDialog(
    show: Boolean,
    onPrimaryButton: () -> Unit,
    onSecondaryButton: () -> Unit = {},
    onDismiss: () -> Unit,
    title: String = "¿Estás seguro de eliminar la aplicación?",
    showTwoButtons: Boolean = true,
) {

    val style1 = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    if (show) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.teal_200),
                ),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 28.dp,
                        bottom = 28.dp
                    )
                ) {

                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white),
                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 18.sp),
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        if (showTwoButtons) {
                            Text(
                                text = "SI",
                                style = style1.copy(fontSize = 18.sp),
                                modifier = Modifier.padding(end = 24.dp).clickable {
                                    onPrimaryButton()
                                    onDismiss()
                                },
                                color = colorResource(id = R.color.white)
                            )
                        }
                        PrimaryButtonComponent(
                            onClicked = {
                                onSecondaryButton()
                                onDismiss()
                            },
                            name = "NO",
                            colorBackground = R.color.purple_500,
                            modifier = Modifier.wrapContentSize()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewWarningOptionsDialog() {
        WarningOptionsDialog(
            show = true,
            onPrimaryButton = {},
            onDismiss = {},
            title = "¿Estás seguro que quieres eliminar el app?",
            onSecondaryButton = {},
            showTwoButtons = true
        )
}
