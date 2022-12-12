package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.repository.DailyActivityRepository
import kotlinx.coroutines.launch

class NewDailyActivityScreenViewModel(
        private val repository: DailyActivityRepository
    ) : ViewModel() {

    fun addDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.addDailyActivity(dailyActivity)
        }
    }
}