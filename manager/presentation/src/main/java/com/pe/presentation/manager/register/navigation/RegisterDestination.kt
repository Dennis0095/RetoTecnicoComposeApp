package com.pe.presentation.manager.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.pe.presentation.manager.apps.navigation.ManagerDestination
import com.pe.presentation.manager.register.ui.RegisterAppScreen

class RegisterDestination {
    companion object {
        //const val ROUTE = "/register_navigation"
        const val ARG_ID_APP = "arg_id_app"
        const val ROUTE_REGISTER = "register/{${ARG_ID_APP}}"
        fun getRouteNavigateRegister(
            idApp: Int
        ): String{
            return ROUTE_REGISTER.replace("{${ManagerDestination.ARG_ID_APP}}", idApp.toString())
        }

        fun argListEmpty() = listOf(
            navArgument(ARG_ID_APP) {
                type = NavType.StringType
                defaultValue = ""
            }
        )
    }
}

fun NavGraphBuilder.registerGraph(
    navigateToBack: () -> Unit,
) {
/*    navigation(
        startDestination = RegisterDestination.ROUTE_REGISTER,
        route = RegisterDestination.ROUTE
    ) {*/
        composable(
            route = RegisterDestination.ROUTE_REGISTER,
            arguments = RegisterDestination.argListEmpty()
        ) { entry ->
            val idApp =
                entry.arguments?.getString(ManagerDestination.ARG_ID_APP)
                    .orEmpty()
            RegisterAppScreen(
                navigateToBack = navigateToBack,
                idApp = idApp
            )
        }
    //}
}