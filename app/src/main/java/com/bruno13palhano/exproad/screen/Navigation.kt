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
    startDestination: String = "mainScreen",
    newDailyViewModel: NewDailyActivityScreenViewModel,
    mainScreenViewModel: MainScreenViewModel,
    editDailyViewModel: EditDailyActivityViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("mainScreen") {
            MainScreen(
                viewModel = mainScreenViewModel,
                onItemClick = { menuItemRoute ->
                    navController.navigate("menuItems/${menuItemRoute}")
                },
                onNavigateToEditDailyActivityScreen = { dailyActivityId ->
                    navController.navigate("editDailyActivityScreen/${dailyActivityId}")
                },
                onNavigateToNewDailyActivityScreen = {
                    navController.navigate("newDailyActivityScreen")
                }
            )
        }

        composable("newDailyActivityScreen") {
            NewDailyActivityScreen(
                viewModel = newDailyViewModel,
                onNavigateUp = {
                    navController.navigateUp()
                },
                onNavigateToMainScreen = {
                    navController.navigate("mainScreen")
                }
            )
        }

        composable(
            "editDailyActivityScreen/{dailyActivityId}",
            arguments = listOf(navArgument("dailyActivityId") {
                type = NavType.LongType
            })
        ) {
            val dailyActivityId = it.arguments?.getLong("dailyActivityId") ?: 0

            EditDailyActivityScreen(
                dailyActivityId = dailyActivityId,
                viewModel = editDailyViewModel,
                onNavigateToMainScreen = {
                    navController.navigate("mainScreen")
                }
            )
        }

        composable(
            route = "menuItems/{menuItem}"
        ) {
            val menuItem = it.arguments?.getString("menuItem")

            when (menuItem) {
                "email" -> navController.navigate("menuItems/email")
                "alarm" -> navController.navigate("menuItems/alarm")
                "analytics" -> navController.navigate("menuItems/analytics")
                "profile" -> navController.navigate("menuItems/profile")
                "settings" -> navController.navigate("menuItems/settings")
                "help" -> navController.navigate("menuItems/help")
            }
        }

        // TODO: remover a raiz das rotas dos menus (menuItems/), colocar as rotas na UtilCompose como final ou Enum.
        //  Extrair as strings hardcoded para o String resources( facilitará as mudanças de idiomas)
        composable(
            route = "menuItems/email"
        ) {
            Text(text = "There's nothing to see here yet on Email Screen.")
        }

        composable(
            route = "menuItems/alarm"
        ) {
            Text(text = "There's nothing to see here yet on Alarm Screen.")
        }

        composable(
            route = "menuItems/analytics"
        ) {
            Text(text = "There's nothing to see here yet on Analytics Screen.")
        }

        composable(
            route = "menuItems/profile"
        ) {
            Text(text = "There's nothing to see here yet on Profile Screen.")
        }

        composable(
            route = "menuItems/settings"
        ) {
            Text(text = "There's nothing to see here yet on Settings Screen.")
        }

        composable(
            route = "menuItems/help"
        ) {
            Text(text = "There's nothing to see here yet on Help Screen.")
        }
    }
}