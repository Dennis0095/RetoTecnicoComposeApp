package com.pe.presentation.manager.apps.ui.detail

import com.pe.presentation.manager.apps.ui.list.ListAppsIntent

sealed class DetailAppIntent{
    object OnNavigateToBack : DetailAppIntent()
    object OnDeleteApp : DetailAppIntent()
    object OnDismissedDialog : DetailAppIntent()
    object OnShoweddDialog : DetailAppIntent()
    object OnNavigationEditApp : DetailAppIntent()
    data class ValidateRooted(val id: Int) : DetailAppIntent()

}
