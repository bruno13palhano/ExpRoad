package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bruno13palhano.activity_repository.DailyActivityRepository

class NewDailyActivityScreenViewModelFactory(
        private val repository: DailyActivityRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewDailyActivityScreenViewModel::class.java)) {
            return NewDailyActivityScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}