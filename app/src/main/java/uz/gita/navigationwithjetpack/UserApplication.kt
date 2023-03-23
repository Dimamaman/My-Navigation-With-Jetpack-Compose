package uz.gita.navigationwithjetpack

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


const val USER_ID_KEY = "userId"

@Composable
fun UserApplication() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "userList") {
        composable(route = "userList") {
            ProfileListScreen(userList, navController)
        }

        composable(
            route = "userDetails/{$USER_ID_KEY}",
            arguments = listOf(navArgument(USER_ID_KEY) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            ProfileDetailsScreen(navBackStackEntry.arguments!!.getInt(USER_ID_KEY), navController)
        }
    }
}


