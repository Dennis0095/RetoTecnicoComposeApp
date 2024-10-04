package com.pe.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectorBottomSheet(
    title: String,
    elements: List<String>,
    onDismiss: () -> Unit,
    onItemClick: (String) -> Unit
) {

    ModalBottomSheet(
        title = title,
        onDismiss = onDismiss,
        content = {
            LazyColumn {
                itemsIndexed(elements) { index, item ->
                    ItemComponent(description = item) {
                        onItemClick(item)
                        onDismiss()
                    }

                    if (index < elements.lastIndex) {
                        DividerListComponent(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
                    }
                }
            }
        })
}

