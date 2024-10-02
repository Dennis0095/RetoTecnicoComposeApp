package com.pe.presentation.manager.register.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.designsystem.component.PrimaryButtonComponent
import com.pe.designsystem.component.SelectorBottomSheet
import com.pe.designsystem.component.SelectorComponent
import com.pe.designsystem.component.TopBarComponent
import com.pe.domain.entity.Selector
import com.pe.manager.domain.entity.Frequency
import com.pe.manager.domain.entity.Priority
import com.pe.manager.domain.entity.StatusApp
import com.pe.manager.domain.entity.TypeApp
import com.pe.presentation.R

@Composable
fun RegisterAppScreen(
    navigateToBack: () -> Unit,
) {
    val viewModel: RegisterAppViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(viewModel.navigation) {
        viewModel.navigation.collect {
            when (it) {
                RegisterAppNavigation.NavigateToBack -> navigateToBack()
                RegisterAppNavigation.NavigateToSuccessful -> {
                    Toast.makeText(
                        context,
                        "La aplicación se ha registrado con éxito.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    RegisterAppScreenContent(
        uiState = uiState,
        onIntent = {
            viewModel.setIntent(it)
        }
    )

    if (uiState.showTypeAppSelector) {
        SelectorBottomSheet(
            title = "Tipo de aplicación",
            elements = TypeApp.entries.map { it.description },
            onDismiss = {
                viewModel.setIntent(RegisterAppIntent.OnDismissedSelector)
            },
            onItemClick = {
                viewModel.setIntent(RegisterAppIntent.OnTypeAppSelected(it))
            }
        )
    }

    if (uiState.showFrequencySelector) {
        SelectorBottomSheet(
            title = "Frecuencia de uso",
            elements = Frequency.entries.map { it.description },
            onDismiss = {
                viewModel.setIntent(RegisterAppIntent.OnDismissedSelector)
            },
            onItemClick = {
                viewModel.setIntent(RegisterAppIntent.OnFrequencySelected(it))
            }
        )
    }
    if (uiState.showStatusSelector) {
        SelectorBottomSheet(
            title = "Estado acutal de la app",
            elements = StatusApp.entries.map { it.description },
            onDismiss = {
                viewModel.setIntent(RegisterAppIntent.OnDismissedSelector)
            },
            onItemClick = {
                viewModel.setIntent(RegisterAppIntent.OnStatusAppSelected(it))
            }
        )
    }
    if (uiState.showPrioritySelector) {
        SelectorBottomSheet(
            title = "Prioridad de la app",
            elements = Priority.entries.map { it.description },
            onDismiss = {
                viewModel.setIntent(RegisterAppIntent.OnDismissedSelector)
            },
            onItemClick = {
                viewModel.setIntent(RegisterAppIntent.OnPrioritySelected(it))
            }
        )
    }


}

@Composable
fun RegisterAppScreenContent(
    uiState: RegisterAppUiState,
    onIntent: (RegisterAppIntent) -> Unit
) {
    Scaffold(containerColor = colorResource(id = R.color.white),
        topBar = {
            TopBarComponent(
                title = "Registro de aplicaciones",
                onBackClicked = {
                    onIntent(RegisterAppIntent.OnNavigateToBack)
                },
            )
        }, bottomBar = {
            PrimaryButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                name = "Guardar",
                onClicked = {
                    onIntent(RegisterAppIntent.RegisterApp)
                },
                showLoading = uiState.showLoadingButton,
                isEnabled = uiState.isEnabled
            )
        }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Datos principales",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            OutlinedTextField(
                value = uiState.nameApp,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateTextName(newText))
                },
                label = { Text("Ingrese su nombre") },
                placeholder = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = uiState.usersActive,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateUsersActive(newText))
                },
                label = { Text("Usuarios activos") },
                placeholder = { Text("Usuarios activos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = uiState.costs,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateCosts(newText))
                },
                label = { Text("Costos operativos") },
                placeholder = { Text("Costos operativos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = uiState.numberOfIncidents,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateNumberOfIncidents(newText))
                },
                label = { Text("Incidencias") },
                placeholder = { Text("Incidencias") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Tipo de aplicación",
                selector = uiState.typeApp?.let { Selector(1, it.description) },
                onClicked = {
                    onIntent(RegisterAppIntent.OnShowedTypeAppSelector)
                },
                isShowArrow = !uiState.isTypeAppSelected,
                isAccountSelected = uiState.isTypeAppSelected,
            )

            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Frecuencia de uso",
                selector = uiState.frequency?.let { Selector(1, it.description) },
                onClicked = {
                    onIntent(RegisterAppIntent.OnShowedFrequencySelector)
                },
                isShowArrow = !uiState.isFrequencySelected,
                isAccountSelected = uiState.isFrequencySelected,
            )
            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Estado",
                selector = uiState.statusApp?.let { Selector(1, it.description) },
                onClicked = {
                    onIntent(RegisterAppIntent.OnShowedStatusSelector)
                },
                isShowArrow = !uiState.isStatusSelected,
                isAccountSelected = uiState.isStatusSelected,
            )
            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Prioridad",
                selector = uiState.priority?.let { Selector(1, it.description) },
                onClicked = {
                    onIntent(RegisterAppIntent.OnShowedPrioritySelector)
                },
                isShowArrow = !uiState.isPrioritySelected,
                isAccountSelected = uiState.isPrioritySelected,
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Recursos consumidos",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = uiState.cpuResource,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateCpuResource(newText))
                },
                label = { Text("CPU (%)") },
                placeholder = { Text("CPU (%)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = uiState.memoryResource,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateMemoryResource(newText))
                },
                label = { Text("Memoria (mb)") },
                placeholder = { Text("Memoria (mb)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = uiState.storageResource,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateStorageResource(newText))
                },
                label = { Text("Almacenamiento (mb)") },
                placeholder = { Text("Almacenamiento (mb)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = uiState.networkConsumptionResource,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateNetworkConsumptionResource(newText))
                },
                label = { Text("Consumo de red Mb/s") },
                placeholder = { Text("Consumo de red Mb/s") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            Text(
                text = "Datos complementarios",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontFamily = FontFamily.Default,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            OutlinedTextField(
                value = uiState.version,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateVersion(newText))
                },
                label = { Text("Ingrese la versión de la app") },
                placeholder = { Text("Versión") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = uiState.keyActivities,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateKeyActivities(newText))
                },
                label = { Text("Actividades claves") },
                placeholder = { Text("Actividades claves") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = uiState.comments,
                onValueChange = { newText ->
                    onIntent(RegisterAppIntent.UpdateComments(newText))
                },
                label = { Text("Comentarios") },
                placeholder = { Text("Comentarios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                maxLines = 5,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}