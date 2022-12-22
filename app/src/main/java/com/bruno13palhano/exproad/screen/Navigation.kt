package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument

@Composable
fun NavigationContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Routes.MAIN_SCREEN.name,
    context: Context,
    owner: ViewModelStoreOwner
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Routes.MAIN_SCREEN.name) {
            MainScreen(
                context = context,
                owner = owner,
                onItemClick = { menuItemRoute ->
                    navController.navigate(menuItemRoute)
                },
                onNavigateToEditDailyActivityScreen = { dailyActivityId ->
                    navController.navigate("${Routes.EDIT_ACTIVITY_SCREEN.name}/${dailyActivityId}")
                },
                onNavigateToNewDailyActivityScreen = {
                    navController.navigate(Routes.NEW_ACTIVITY_SCREEN.name)
                }
            )
        }

        composable(Routes.NEW_ACTIVITY_SCREEN.name) {
            NewDailyActivityScreen(
                context = context,
                owner = owner,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.MAIN_SCREEN.name)
                }
            )
        }

        composable(
            "${Routes.EDIT_ACTIVITY_SCREEN.name}/{${Routes.DAILY_ACTIVITY_ID.name}}",
            arguments = listOf(navArgument(Routes.DAILY_ACTIVITY_ID.name) {
                type = NavType.LongType
            })
        ) {
            val dailyActivityId = it.arguments?.getLong(Routes.DAILY_ACTIVITY_ID.name) ?: 0

            EditDailyActivityScreen(
                dailyActivityId = dailyActivityId,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.MAIN_SCREEN.name)
                },
                context = context,
                owner = owner
            )
        }

        composable(
            route = "{${Routes.MENU_ITEM.name}}"
        ) {
            val menuItem = it.arguments?.getString(Routes.MENU_ITEM.name)

            when (menuItem) {
                Routes.EMAIL.name -> navController.navigate(Routes.EMAIL.name)
                Routes.ALARM.name -> navController.navigate(Routes.ALARM.name)
                Routes.ANALYTICS.name -> navController.navigate(Routes.ANALYTICS.name)
                Routes.PROFILE.name -> navController.navigate(Routes.PROFILE.name)
                Routes.SETTINGS.name -> navController.navigate(Routes.SETTINGS.name)
                Routes.HELP.name -> navController.navigate(Routes.HELP.name)
            }
        }

        composable(
            route = Routes.EMAIL.name
        ) {
            Text(text = "There's nothing to see here yet on Email Screen.")
        }

        composable(
            route = Routes.ALARM.name
        ) {
            Text(text = "There's nothing to see here yet on Alarm Screen.")
        }

        composable(
            route = Routes.ANALYTICS.name
        ) {
            DailyActivityAnalyticsScreen(
                context = context,
                owner = owner,
            ) {
                navController.navigateUp()
            }
        }

        composable(
            route = Routes.PROFILE.name
        ) {
            Text(text = "There's nothing to see here yet on Profile Screen.")
        }

        composable(
            route = Routes.SETTINGS.name
        ) {
            Text(text = "There's nothing to see here yet on Settings Screen.")
        }

        composable(
            route = Routes.HELP.name
        ) {
            Text(text = "There's nothing to see here yet on Help Screen.")
        }
    }
}