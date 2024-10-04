package com.pe.presentation.manager.apps.ui.list

import com.pe.manager.domain.entity.RegisterApp

sealed class ListAppsIntent{
    object OnNavigateToBack : ListAppsIntent()
    data class OnShoweddDialog(val appRegistered: RegisterApp) : ListAppsIntent()
    object OnDismissedDialog : ListAppsIntent()
    data class OnNavigateToDetailApp(val appRegistered: RegisterApp) : ListAppsIntent()
    data class UpdateRecomendation(val recomendation: String, val appRegistered: RegisterApp) : ListAppsIntent()
    data class UpdateShowRecomendation(val appRegistered: RegisterApp) : ListAppsIntent()
    data class OnDeletApp(val appRegistered: RegisterApp) : ListAppsIntent()
    object ValidateRooted : ListAppsIntent()

}
