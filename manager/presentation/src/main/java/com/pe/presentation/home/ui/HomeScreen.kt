package com.pe.presentation.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.designsystem.component.OptionComponent
import com.pe.domain.utils.optionsKripto

@Composable
fun HomeScreen(
    navigateToRegister: () -> Unit,
    navigateToList: () -> Unit,

    ) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("KRIPTO", color = Color.Black, modifier = Modifier.padding(top = 30.dp), style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        ))
        Spacer(modifier = Modifier.height(80.dp))

        optionsKripto.forEach { option ->
            OptionComponent(
                name = option.description,
                modifier = Modifier.fillMaxWidth(),
                onClicked = {
                    if (option.description == "Registro") {
                        navigateToRegister()
                    } else {
                        navigateToList()
                    }
                },
            )
        }

    }


}