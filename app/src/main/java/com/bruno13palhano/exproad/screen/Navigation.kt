package com.bruno13palhano.exproad.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bruno13palhano.exproad.viewmodel.EditDailyActivityViewModel
import com.bruno13palhano.exproad.viewmodel.MainScreenViewModel
import com.bruno13palhano.exproad.viewmodel.NewDailyActivityScreenViewModel

@Composable
fun NavigationContent(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.MAINSCREEN.name,
    newDailyViewModel: NewDailyActivityScreenViewModel,
    mainScreenViewModel: MainScreenViewModel,
    editDailyViewModel: EditDailyActivityViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.MAINSCREEN.name) {
            MainScreen(
                viewModel = mainScreenViewModel,
                onItemClick = { menuItemRoute ->
                    navController.navigate(menuItemRoute)
                },
                onNavigateToEditDailyActivityScreen = { dailyActivityId ->
                    navController.navigate("${Routes.EDITACTIVITYSCREEN.name}/${dailyActivityId}")
                },
                onNavigateToNewDailyActivityScreen = {
                    navController.navigate(Routes.NEWACTIVITYSCREEN.name)
                }
            )
        }

        composable(Routes.NEWACTIVITYSCREEN.name) {
            NewDailyActivityScreen(
                viewModel = newDailyViewModel,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.MAINSCREEN.name)
                }
            )
        }

        composable(
            "${Routes.EDITACTIVITYSCREEN.name}/{dailyActivityId}",
            arguments = listOf(navArgument("dailyActivityId") {
                type = NavType.LongType
            })
        ) {
            val dailyActivityId = it.arguments?.getLong("dailyActivityId") ?: 0

            EditDailyActivityScreen(
                dailyActivityId = dailyActivityId,
                viewModel = editDailyViewModel,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.MAINSCREEN.name)
                }
            )
        }

        composable(
            route = "{menuItem}"
        ) {
            val menuItem = it.arguments?.getString("menuItem")

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
            Text(text = "There's nothing to see here yet on Analytics Screen.")
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