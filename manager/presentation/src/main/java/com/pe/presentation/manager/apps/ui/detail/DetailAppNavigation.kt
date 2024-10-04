package com.pe.presentation.manager.apps.ui.detail

sealed class DetailAppNavigation {
    object NavigateToBack : DetailAppNavigation()
    object NavigateToSuccessful : DetailAppNavigation()
    data class NavigateToEditApp(val idApp: Int) : DetailAppNavigation()
}
