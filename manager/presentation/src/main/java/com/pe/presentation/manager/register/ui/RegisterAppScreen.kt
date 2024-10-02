package com.pe.presentation.manager.register.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.designsystem.component.OptionComponent
import com.pe.designsystem.component.SelectorComponent
import com.pe.designsystem.component.TopBarComponent
import com.pe.domain.entity.Selector
import com.pe.presentation.R

@Preview()
@Composable
fun RegisterAppScreen(){
    val viewModel: RegisterAppViewModel = hiltViewModel()
    val state by viewModel.screenState.collectAsState()


    Scaffold(containerColor = colorResource(id = R.color.white),
        topBar = {
            TopBarComponent(
                title = "Registro de aplicaciones",
                onBackClicked = { },
            )
        }, bottomBar = {
            OptionComponent(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                name = "Guardar",
                onClicked = {}
            )
        }){paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues).verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Datos principales", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), fontFamily = FontFamily.Default, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ))
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Ingrese su nombre") },
                placeholder = { Text("Nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Usuarios activos") },
                placeholder = { Text("Usuarios activos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Costos operativos") },
                placeholder = { Text("Costos operativos") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Incidencias") },
                placeholder = { Text("Incidencias") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Tipo de aplicación",
                selector = Selector(1, "Operacional"),
                onClicked = {
                },
                isShowArrow = true,
                isAccountSelected = false,
            )

            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Frecuencia de uso",
                selector = Selector(1, "Operacional"),
                onClicked = {
                },
                isShowArrow = true,
                isAccountSelected = false,
            )
            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Estado",
                selector = Selector(1, "Operacional"),
                onClicked = {
                },
                isShowArrow = true,
                isAccountSelected = false,
            )
            SelectorComponent(
                modifier = Modifier.padding(top = 20.dp),
                header = "Prioridad",
                selector = Selector(1, "Operacional"),
                onClicked = {
                },
                isShowArrow = true,
                isAccountSelected = false,
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Recursos consumidos", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), fontFamily = FontFamily.Default, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ))
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("CPU (%)") },
                placeholder = { Text("CPU (%)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Memoria (mb)") },
                placeholder = { Text("Memoria (mb)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Almacenamiento (mb)") },
                placeholder = { Text("Almacenamiento (mb)") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Consumo de red Mb/s") },
                placeholder = { Text("Consumo de red Mb/s") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            Text(text = "Datos compleentarios", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), fontFamily = FontFamily.Default, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ))

            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Ingrese la versión de la app") },
                placeholder = { Text("Versión") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Actividades claves") },
                placeholder = { Text("Actividades claves") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                maxLines = 5
            )
            OutlinedTextField(
                value = "",
                onValueChange = { newText ->
                    //onEvent(LoginScreenEvent.OnUserChanged(newText))
                },
                label = { Text("Comentarios") },
                placeholder = { Text("Comentarios") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                maxLines = 5
            )
        }
    }
}