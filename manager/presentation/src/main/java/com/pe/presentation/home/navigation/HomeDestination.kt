package com.pe.presentation.home.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pe.presentation.home.ui.HomeScreen

class HomeDestination{
    companion object {
        const val ROUTE = "/home"
        const val ROUTE_GRAPH = "home_graph"
    }
}

fun NavGraphBuilder.homeGraph(
    navigateToList: () -> Unit,
    navigateToRegister: () -> Unit,
){

    navigation(
        startDestination = HomeDestination.ROUTE,
        route = HomeDestination.ROUTE_GRAPH
    ) {
        composable(
            route = HomeDestination.ROUTE
        ) {
            HomeScreen(
                navigateToRegister = navigateToRegister,
                navigateToList = navigateToList,
            )

        }

    }

}