package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bruno13palhano.activity_repository.DailyActivityAnalyticsRepository

class DailyActivityAnalyticsViewModelFactory(
    private val repository: DailyActivityAnalyticsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DailyActivityAnalyticsViewModel::class.java)) {
            return DailyActivityAnalyticsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}