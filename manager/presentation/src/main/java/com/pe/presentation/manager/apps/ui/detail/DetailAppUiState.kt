package com.pe.presentation.manager.apps.ui.detail

import com.pe.manager.domain.entity.RegisterApp

data class DetailAppUiState(
    val appRegistered: RegisterApp = RegisterApp(),
    val showLoadingButton: Boolean = false,
    val showDialog: Boolean = false,
)
