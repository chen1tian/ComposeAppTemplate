package com.welldone.composeapptemplate.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.welldone.composeapptemplate.ui.screens.HomeScreen


/**
 * 导航宿主
 */
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    // 导航控制器
    navController: NavHostController,
    // 起始导航项
    startDestination: String = NavigationItem.HOME.route,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        // 主界面
        //            HomeScreen(
        composable(NavigationItem.HOME.route) {
            // 主界面
            HomeScreen()
        }
    }
}