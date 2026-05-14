package com.sasquer.pizzas.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sasquer.pizzas.ui.detail.PizzaDetailScreen
import com.sasquer.pizzas.ui.main.MainScreen
import com.sasquer.pizzas.ui.splash.SplashScreen

@Composable
fun PizzaNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onAnimationFinished = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                onReadyToNavigate = { startIndex ->
                    navController.navigate(Screen.PizzaDetail.createRoute(startIndex)) {
                        popUpTo(Screen.Main.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = Screen.PizzaDetail.route,
            arguments = listOf(
                navArgument("startIndex") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            PizzaDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }

    }

}