package com.pe.presentation.manager.apps.ui.detail

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.designsystem.component.PrimaryButtonComponent
import com.pe.designsystem.component.TopBarComponent
import com.pe.presentation.R
import com.pe.presentation.component.ItemDetailAppComponent
import com.pe.presentation.component.WarningOptionsDialog
import com.pe.presentation.util.registerApp


@Composable
fun DetailAppScreen(
    navigateToBack: () -> Unit,
    navigateToEditApp: (Int) -> Unit,
    idApp: Int
) {

    val viewModel: DetailAppViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setIntent(DetailAppIntent.ValidateRooted(idApp))
    }
    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                DetailAppNavigation.NavigateToBack -> navigateToBack()
                is DetailAppNavigation.NavigateToEditApp -> navigateToEditApp(it.idApp)
                DetailAppNavigation.NavigateToSuccessful -> {
                    Toast.makeText(
                        context,
                        "La aplicación se ha eliminado con éxito.",
                        Toast.LENGTH_SHORT
                    ).show()
                    navigateToBack()
                }
            }
        }

    }

    DetailAppScreenContent(
        uiState = uiState,
        onIntent = {
            viewModel.setIntent(it)
        }
    )
}

@Composable
fun DetailAppScreenContent(
    uiState: DetailAppUiState,
    onIntent: (DetailAppIntent) -> Unit
) {

    val style1 = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    if (uiState.showDialog) {
        var showDialog by remember { mutableStateOf(true) }
        WarningOptionsDialog(
            show = showDialog,
            onPrimaryButton = {
                onIntent(DetailAppIntent.OnDeleteApp)
            },
            onSecondaryButton = {
                onIntent(DetailAppIntent.OnDismissedDialog)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }

    Scaffold(containerColor = colorResource(id = R.color.white),
        topBar = {
            TopBarComponent(
                title = "Detalle de aplicación",
                onBackClicked = {
                    onIntent(DetailAppIntent.OnNavigateToBack)
                },
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 4.dp)
            ) {

                PrimaryButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    name = "Eliminar",
                    onClicked = {
                        onIntent(DetailAppIntent.OnShoweddDialog)
                    },
                    showLoading = uiState.showLoadingButton,
                    colorBackground = R.color.red,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Editar",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onIntent(DetailAppIntent.OnNavigationEditApp)
                        },
                    textAlign = TextAlign.Center,
                    style = style1.copy(fontSize = 18.sp)
                )
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = uiState.appRegistered.nameApp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                style = style1.copy(fontSize = 20.sp, color = colorResource(id = R.color.teal_700))
            )
            Spacer(modifier = Modifier.height(12.dp))
            ItemDetailAppComponent(
                "Tipo de aplicación:",
                uiState.appRegistered.typeApp?.description.orEmpty()
            )
            ItemDetailAppComponent(
                "Estado actual:",
                uiState.appRegistered.statusApp?.description.orEmpty()
            )
            ItemDetailAppComponent(
                "Prioridad:",
                uiState.appRegistered.priority?.description.orEmpty()
            )
            ItemDetailAppComponent(
                "Frecuencia de uso:",
                uiState.appRegistered.frequency?.description.orEmpty()
            )
            ItemDetailAppComponent("Costos operativos:", uiState.appRegistered.costs)
            ItemDetailAppComponent("Usuarios activos:", uiState.appRegistered.usersActive)
            ItemDetailAppComponent("Número de incidentes:", uiState.appRegistered.numberOfIncidents)

            Text(
                text = "Actividades claves",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                style = style1.copy(fontSize = 16.sp)
            )
            Text(
                text = uiState.appRegistered.keyActivities,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Text(
                text = "Comentarios",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
                style = style1.copy(fontSize = 16.sp)
            )
            Text(
                text = uiState.appRegistered.comments,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Text(
                text = "Recomendaciones",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
                style = style1.copy(fontSize = 16.sp)
            )
            Text(
                text = uiState.appRegistered.recommendations,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun DetailAppScreenContentPreview() {
    DetailAppScreenContent(
        uiState = registerApp,
        onIntent = {}
    )
}