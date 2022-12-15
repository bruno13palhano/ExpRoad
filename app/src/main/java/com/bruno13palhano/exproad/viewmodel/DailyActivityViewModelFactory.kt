package com.bruno13palhano.exproad.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.activity_repository.DailyActivityRepositoryFactory

class DailyActivityViewModelFactory(
    context: Context,
    private val owner: ViewModelStoreOwner
) {
    private val repositoryFactory = DailyActivityRepositoryFactory(context)

    fun createEditDailyActivityViewModel(): EditDailyActivityViewModel {
        val editViewModelFactory =
            EditDailyActivityViewModelFactory(repositoryFactory.createDailyActivityRepositoryDB())

        return ViewModelProvider(owner, editViewModelFactory)[EditDailyActivityViewModel::class.java]
    }

    fun createNewDailyActivityViewModel(): NewDailyActivityScreenViewModel {
        val newViewModelFactory =
            NewDailyActivityScreenViewModelFactory(repositoryFactory.createDailyActivityRepositoryDB())

        return ViewModelProvider(owner, newViewModelFactory)[NewDailyActivityScreenViewModel::class.java]
    }

    fun createMainViewModel(): MainScreenViewModel {
        val mainViewModel =
            MainScreenViewModelFactory(repositoryFactory.createDailyActivityRepositoryDB())

        return ViewModelProvider(owner, mainViewModel)[MainScreenViewModel::class.java]
    }

    fun createDailyActivityAnalyticsViewModel(): ViewModel {
        val analyticsViewModel =
            DailyActivityAnalyticsViewModelFactory(repositoryFactory.createDailyActivityAnalyticsRepositoryDB())

        return ViewModelProvider(owner, analyticsViewModel)[DailyActivityAnalyticsViewModel::class.java]
    }
}