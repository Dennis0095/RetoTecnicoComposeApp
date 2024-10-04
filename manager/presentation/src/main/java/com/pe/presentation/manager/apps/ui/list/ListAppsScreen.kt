package com.pe.presentation.manager.apps.ui.list


import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.designsystem.component.TopBarComponent
import com.pe.presentation.R
import com.pe.presentation.component.ItemAppComponent
import com.pe.presentation.component.WarningOptionsDialog
import com.pe.presentation.manager.apps.ui.detail.DetailAppIntent
import com.pe.presentation.util.recommendDeleting

@Composable
fun ListAppsScreen(
    navigateToBack: () -> Unit,
    navigateToDetailApp: (Int) -> Unit,
    ){
    val viewModel: ListAppsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.setIntent(ListAppsIntent.ValidateRooted)
    }

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                ListAppsNavigation.NavigateToBack -> navigateToBack()
                is ListAppsNavigation.NavigateToDetailApp -> navigateToDetailApp(it.idApp)
                ListAppsNavigation.NavigateToSuccessful -> {
                    Toast.makeText(
                        context,
                        "La aplicación se ha eliminado con éxito.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    ListAppsScreenContent(
        uiState = uiState,
        onIntent = {
            viewModel.setIntent(it)
        }
    )
}

@Composable
fun ListAppsScreenContent(
    uiState: ListAppsUiState,
    onIntent: (ListAppsIntent) -> Unit
){
    if(uiState.showDialog){
        var showDialog by remember { mutableStateOf(true) }
        WarningOptionsDialog(
            show = showDialog,
            onPrimaryButton = {
                onIntent(ListAppsIntent.OnDeletApp(uiState.appSelected))
            },
            onSecondaryButton = {
                onIntent(ListAppsIntent.OnDismissedDialog)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
    Scaffold(containerColor = colorResource(id = R.color.white),
        topBar = {
            TopBarComponent(
                title = "Aplicaciones registradas",
                onBackClicked = {
                    onIntent(ListAppsIntent.OnNavigateToBack)
                },
            )
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            if (uiState.listApps.isEmpty()) {
                item {
                    Text(
                        text = "No hay aplicaciones registradas",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                items(uiState.listApps){ appRegistered ->
                    //onIntent(ListAppsIntent.UpdateRecomendation(recommendDeleting(appRegistered).recommendation, appRegistered))

                    ItemAppComponent(uiState = appRegistered, navigateToDetailApp = {
                        onIntent(ListAppsIntent.OnNavigateToDetailApp(appRegistered))
                    }, deleteApp = {
                        onIntent(ListAppsIntent.OnShoweddDialog(appRegistered))
                    }, showDelete = appRegistered.recommendations.isNotEmpty(), updateShowRecommendations = {
                        onIntent(ListAppsIntent.UpdateShowRecomendation(appRegistered))
                    }, showRecommendations = appRegistered.showRecommendation)
                }
            }
        }
    }
}