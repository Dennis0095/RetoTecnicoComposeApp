package com.pe.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.pe.designsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(
    title: String,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    androidx.compose.material3.ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { },
        containerColor = colorResource(id = R.color.bottom_sheet_content),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
    ) {
        HeaderBottomSheet(title = title)

        Box(modifier = Modifier.padding(bottom = 32.dp, top = 6.dp)) {
            content()
        }
    }
}