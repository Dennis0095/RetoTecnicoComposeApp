package com.pe.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pe.presentation.home.navigation.HomeDestination
import com.pe.presentation.home.navigation.homeGraph
import com.pe.presentation.manager.apps.navigation.ManagerDestination
import com.pe.presentation.manager.apps.navigation.managerGraph
import com.pe.presentation.manager.register.navigation.RegisterDestination
import com.pe.presentation.manager.register.navigation.registerGraph


@Composable
fun KriptoNagivationHost(
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = HomeDestination.ROUTE_GRAPH,
    ) {
        homeGraph(
            navigateToList = {
                navController.navigate(ManagerDestination.ROUTE_LIST)
            },
            navigateToRegister = {
                navController.navigate(RegisterDestination.getRouteNavigateRegister(idApp = 999999))
            }
        )

        registerGraph (
            navigateToBack = {
                navController.popBackStack()
            }
        )
        managerGraph (
            navigateToBack = {
                navController.popBackStack()
            },
            navigateToDetailApp = {
                navController.navigate(ManagerDestination.getRouteNavigateDetail(idApp = it))
            },
            navigateToEditApp = {
                navController.navigate(RegisterDestination.getRouteNavigateRegister(idApp = it))
            },

        )

    }

}