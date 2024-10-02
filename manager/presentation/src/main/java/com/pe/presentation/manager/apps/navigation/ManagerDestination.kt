package com.pe.presentation.manager.apps.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.pe.presentation.manager.apps.ui.detail.DetailAppScreen
import com.pe.presentation.manager.apps.ui.list.ListAppsScreen

class ManagerDestination {
    companion object {
        const val ROUTE_LIST = "list"
        const val ROUTE_DETAIL_APP = "detail_app"
    }
}

fun NavGraphBuilder.managerGraph(
    navigateToBack: () -> Unit,
) {

    composable(
        route = ManagerDestination.ROUTE_LIST

    ) {
        ListAppsScreen()
    }

    composable(
        route = ManagerDestination.ROUTE_DETAIL_APP

    ) {
        DetailAppScreen()
    }

}