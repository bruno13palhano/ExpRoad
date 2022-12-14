package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bruno13palhano.activity_repository.DailyActivityRepository

class EditDailyActivityViewModelFactory(
    private val repository: DailyActivityRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditDailyActivityViewModel::class.java)) {
            return EditDailyActivityViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}