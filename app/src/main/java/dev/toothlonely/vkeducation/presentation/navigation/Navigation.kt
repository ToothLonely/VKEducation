package dev.toothlonely.vkeducation.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.toothlonely.vkeducation.data.STUB
import dev.toothlonely.vkeducation.presentation.screen.appdetails.AppDetailsScreen
import dev.toothlonely.vkeducation.presentation.screen.appslist.AppsListScreen
import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object AppsList : Screen

    @Serializable
    data class AppInfo(val applicationId: Int) : Screen

    @Serializable
    data class AppDetail(val applicationName: String) : Screen
}

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AppsList
    ) {
        composable<Screen.AppsList> {
            AppsListScreen(modifier = modifier) { destination ->
                navController.navigate(destination)
            }
        }

        composable<Screen.AppDetail> { backStackEntry ->
/*            val appItem = backStackEntry.toRoute<Screen.AppInfo>()
            AppInfoScreen(applicationId = appItem.applicationId)*/
            val appName = backStackEntry.toRoute<Screen.AppDetail>().applicationName
            val appItem = STUB.getAppByName(appName)
            AppDetailsScreen()
        }
    }
}