package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.repository.DailyActivityRepository
import kotlinx.coroutines.launch

class NewDailyActivityScreenViewModel(
        private val repository: DailyActivityRepository
    ) : ViewModel() {

    var dailyActivityList: LiveData<List<DailyActivity>>

    init {
        dailyActivityList = getDailyActivities(0, 10)
    }

    fun addDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.addDailyActivity(dailyActivity)
        }
    }

    fun updateDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.updateDailyActivity(dailyActivity)
        }
    }

    fun deleteDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.deleteDailyActivity(dailyActivity)
        }
    }

    fun getDailyActivities(offset: Int, limit: Int): LiveData<List<DailyActivity>> {
        return repository.getDailyActivities(offset, limit)
    }

    fun getAll(): LiveData<List<DailyActivity>> {
        return repository.getAll()
    }
}