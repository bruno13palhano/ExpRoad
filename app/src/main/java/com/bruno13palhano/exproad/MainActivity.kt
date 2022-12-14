package com.bruno13palhano.exproad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.lifecycle.ViewModelProvider
import com.bruno13palhano.activity_repository.DailyActivityRepositoryFactory
import com.bruno13palhano.exproad.screen.NavigationContent
import com.bruno13palhano.exproad.ui.theme.ExpRoadTheme
import com.bruno13palhano.exproad.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = DailyActivityRepositoryFactory(this).createDailyActivityRepositoryDB()

        val newDailyViewModelFactory = NewDailyActivityScreenViewModelFactory(repository)
        val newDailyViewModel = ViewModelProvider(this, newDailyViewModelFactory)[NewDailyActivityScreenViewModel::class.java]

        val editDailyViewModelFactory = EditDailyActivityViewModelFactory(repository)
        val editDailyViewModel = ViewModelProvider(this, editDailyViewModelFactory)[EditDailyActivityViewModel::class.java]

        val mainScreenViewModelFactory = MainScreenViewModelFactory(repository)
        val mainScreenViewModel = ViewModelProvider(this, mainScreenViewModelFactory)[MainScreenViewModel::class.java]

        setContent {
            ExpRoadTheme {
                Surface {
                    NavigationContent(
                        newDailyViewModel = newDailyViewModel,
                        mainScreenViewModel = mainScreenViewModel,
                        editDailyViewModel = editDailyViewModel
                    )
                }
            }
        }
    }
}