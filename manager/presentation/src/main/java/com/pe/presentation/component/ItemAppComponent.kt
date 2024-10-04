package com.pe.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.component.PrimaryButtonComponent
import com.pe.manager.domain.entity.RegisterApp
import com.pe.presentation.R

@Composable
fun ItemAppComponent(
    showRecommendations: Boolean = false,
    showDelete: Boolean = false,
    uiState: RegisterApp,
    navigateToDetailApp: () -> Unit,
    deleteApp: () -> Unit,
    updateShowRecommendations: () -> Unit,
) {

    val style1 = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    navigateToDetailApp()
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = uiState.nameApp,
                        modifier = Modifier
                            .weight(1f),
                        textAlign = TextAlign.Start,
                        style = style1
                    )
                    Text(
                        text = "Versión " + uiState.version,
                        textAlign = TextAlign.End
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.navegador),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.typeApp?.description.orEmpty())
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.aplication),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.statusApp?.description.orEmpty())
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.prioridad),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.priority?.description.orEmpty())
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.frequency),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.frequency?.description.orEmpty())
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_users_24),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.usersActive)
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.incidente),
                            contentDescription = ""
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = uiState.numberOfIncidents)
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Costo operacional",
                        textAlign = TextAlign.Start,
                        style = style1.copy(fontSize = 14.sp)
                    )
                    Text(
                        text = "S/ " + uiState.costs,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }


                if(uiState.recommendations.isNotEmpty()){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .clickable {
                                updateShowRecommendations()
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recomendaciones",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    updateShowRecommendations()
                                },
                            textAlign = TextAlign.Start,
                            style = style1.copy(fontSize = 14.sp)
                        )
                        Icon(
                            painter = painterResource(id = com.pe.designsystem.R.drawable.ic_arrow_down),
                            contentDescription = "Icon bancom watermaker",
                            tint = colorResource(id = R.color.black),
                            modifier = Modifier
                                .width(20.dp)
                                .height(15.dp)
                                .clickable {
                                    updateShowRecommendations()
                                }
                        )
                    }
                }


                if (showRecommendations) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = uiState.recommendations,
                            textAlign = TextAlign.Start
                        )
                    }
                }

                if (showDelete) {
                    PrimaryButtonComponent(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        name = "Eliminar",
                        onClicked = {
                            deleteApp()
                        },
                        colorBackground = R.color.red,
                        showLoading = false,//uiState.showLoadingButton,
                        isEnabled = true//uiState.isEnabled
                    )
                }


            }

        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun ItemAppComponentPreview() {
    ItemAppComponent(uiState = RegisterApp(
        nameApp = "Cambista",
        showRecommendation = true,
        recommendations = "dsfdsfsdf"
    ), deleteApp = {}, navigateToDetailApp = {}, updateShowRecommendations = {})
}