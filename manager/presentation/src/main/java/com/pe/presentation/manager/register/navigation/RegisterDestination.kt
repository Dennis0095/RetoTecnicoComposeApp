package com.pe.presentation.manager.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pe.presentation.manager.register.ui.RegisterAppScreen

class RegisterDestination {
    companion object {
        const val ROUTE = "/register_navigation"
        const val ROUTE_REGISTER = "register"
    }
}

fun NavGraphBuilder.registerGraph(
    navigateToBack: () -> Unit,
) {
    navigation(
        startDestination = RegisterDestination.ROUTE_REGISTER,
        route = RegisterDestination.ROUTE
    ) {
        composable(
            route = RegisterDestination.ROUTE_REGISTER

        ) {
            RegisterAppScreen()
        }
    }
}