package com.pe.presentation.manager.apps.ui.list

import com.pe.manager.domain.entity.RegisterApp

data class ListAppsUiState(
    val text: String = "",
    val showDialog: Boolean = false,
    val appSelected: RegisterApp = RegisterApp(),
    val listApps: ArrayList<RegisterApp> = arrayListOf()
)
