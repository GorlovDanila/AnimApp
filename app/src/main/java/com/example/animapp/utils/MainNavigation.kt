package com.example.animapp.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animapp.R
import com.example.animapp.presentation.ui.DetailsScreen
import com.example.animapp.presentation.ui.MainScreen

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int,
) {

    object Main : Screen(
        route = "main",
        name = R.string.screen_main,
    )

    object Detail : Screen(
        route = "details/{animId}",
        name = R.string.screen_detail,
    )
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Main
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {

        composable(Screen.Main.route) { MainScreen(navController = navController) }

        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("animId") {type = NavType.IntType})
        ) {backStackEntry ->
            DetailsScreen(id = backStackEntry.arguments?.getInt("animId") ?: 0) }
    }
}
