package com.pe.presentation.manager.apps.ui.list

sealed class ListAppsNavigation {
    object NavigateToBack : ListAppsNavigation()
    object NavigateToSuccessful : ListAppsNavigation()

    data class NavigateToDetailApp(val idApp: Int) : ListAppsNavigation()

}
