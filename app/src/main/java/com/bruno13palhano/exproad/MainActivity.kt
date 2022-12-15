package com.bruno13palhano.exproad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bruno13palhano.activity_repository.DailyActivityRepositoryFactory
import com.bruno13palhano.exproad.screen.NavigationContent
import com.bruno13palhano.exproad.ui.theme.ExpRoadTheme
import com.bruno13palhano.exproad.viewmodel.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var newDailyViewModel: NewDailyActivityScreenViewModel;
    private lateinit var mainScreenViewModel: MainScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val repositoryFactory = DailyActivityRepositoryFactory(this)
//
//        val activityRepository = repositoryFactory.createDailyActivityRepositoryDB()
//
//        val newDailyViewModelFactory = NewDailyActivityScreenViewModelFactory(activityRepository)
//        newDailyViewModel = ViewModelProvider(this, newDailyViewModelFactory)[NewDailyActivityScreenViewModel::class.java]
//
//        val editDailyViewModelFactory = EditDailyActivityViewModelFactory(activityRepository)
//        val editDailyViewModel = ViewModelProvider(this, editDailyViewModelFactory)[EditDailyActivityViewModel::class.java]
//
//        val mainScreenViewModelFactory = MainScreenViewModelFactory(activityRepository)
//        mainScreenViewModel = ViewModelProvider(this, mainScreenViewModelFactory)[MainScreenViewModel::class.java]
//
//        val repositoryAnalytics = repositoryFactory.createDailyActivityAnalyticsRepositoryDB()



        setContent {

            val navController: NavHostController = rememberNavController()

            ExpRoadTheme {
                Surface {
                    NavigationContent(
                        navController = navController,
//                        newDailyViewModel = newDailyViewModel,
//                        mainScreenViewModel = mainScreenViewModel,
//                        editDailyViewModel = editDailyViewModel
                        context = this,
                        owner = this
                    )
                }
            }
        }
    }
}