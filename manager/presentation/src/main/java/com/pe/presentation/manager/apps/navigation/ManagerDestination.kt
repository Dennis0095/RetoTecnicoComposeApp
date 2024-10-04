package com.pe.presentation.manager.apps.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pe.presentation.manager.apps.ui.detail.DetailAppScreen
import com.pe.presentation.manager.apps.ui.list.ListAppsScreen

class ManagerDestination {
    companion object {
        const val ROUTE_LIST = "list"
        const val ARG_ID_APP = "arg_id_app"
        const val ROUTE_DETAIL_APP: String = "detail_app/{$ARG_ID_APP}"

        fun getRouteNavigateDetail(
            idApp: Int
        ): String{
            return ROUTE_DETAIL_APP.replace("{$ARG_ID_APP}", idApp.toString())
        }

        fun argListEmpty() = listOf(
            navArgument(ARG_ID_APP) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    }
}

fun NavGraphBuilder.managerGraph(
    navigateToBack: () -> Unit,
    navigateToDetailApp: (Int) -> Unit,
    navigateToEditApp: (Int) -> Unit,
) {
    composable(
        route = ManagerDestination.ROUTE_LIST
    ) {
        ListAppsScreen(
            navigateToBack = navigateToBack,
            navigateToDetailApp = navigateToDetailApp,
        )
    }

    composable(
        route = ManagerDestination.ROUTE_DETAIL_APP,
        arguments = ManagerDestination.argListEmpty()
    ) {entry ->
        val idApp =
            entry.arguments?.getString(ManagerDestination.ARG_ID_APP)
                .orEmpty()
        DetailAppScreen(
            navigateToBack = navigateToBack,
            idApp = idApp.toInt(),
            navigateToEditApp = navigateToEditApp
        )
    }

}