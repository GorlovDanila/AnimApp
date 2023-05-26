package com.example.animapp.utils.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animapp.R
import com.example.animapp.presentation.ui.DetailsScreen
import com.example.animapp.presentation.ui.MainScreen
import com.example.animapp.presentation.ui.SettingsScreen
import com.example.animapp.presentation.ui.TemplateScreen
import com.example.animapp.presentation.ui.theme.MyTheme

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int,
    val icon: ImageVector,
) {

    object Main : Screen(
        route = "main",
        name = R.string.screen_main,
        icon = Icons.Filled.Home
    )

    object Detail : Screen(
        route = "details/{animId}",
        name = R.string.screen_detail,
        icon = Icons.Filled.List
    )

    object Settings : Screen(
        route = "settings",
        name = R.string.screen_settings,
        icon = Icons.Filled.Settings,
    )

    object Template : Screen(
        route = "template",
        name = R.string.screen_template,
        icon = Icons.Filled.Add,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Main
) {

    val items = listOf(
        Screen.Template,
        Screen.Main,
        Screen.Settings
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MyTheme.colors.tintColor,
                elevation = 12.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = null
                        )
                    },
                        label = {
                            Text(
                                text = stringResource(id = screen.name),
                                style = MyTheme.typography.globalTextStyle
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination.route
        ) {
            composable(Screen.Main.route) { MainScreen(navController = navController) }
            composable(Screen.Settings.route) { SettingsScreen() }
            composable(Screen.Template.route) { TemplateScreen() }
            composable(
                Screen.Detail.route,
                arguments = listOf(navArgument("animId") { type = NavType.IntType })
            ) { backStackEntry ->
                DetailsScreen(id = backStackEntry.arguments?.getInt("animId") ?: 0)
            }
        }
    }
}
