package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.DailyActivityRepository
import com.bruno13palhano.activity_repository.model.DailyActivityImpl

class MainScreenViewModel(
    private val repository: DailyActivityRepository
) : ViewModel() {

    fun getDailyActivities(offset: Int, limit: Int): LiveData<List<DailyActivity>> {
        return repository.getDailyActivities(offset, limit)
    }

    fun getAll(): LiveData<List<DailyActivity>> {
        return repository.getAll()
    }
}