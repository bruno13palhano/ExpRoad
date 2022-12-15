package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.DailyActivityAnalyticsRepository
import java.util.*

class DailyActivityAnalyticsViewModel(
    private val repository: DailyActivityAnalyticsRepository
) : ViewModel() {

    fun getAnalyticsByTitle(activityTitle: String): LiveData<List<DailyActivity>> {
        return repository.getByTitle(activityTitle)
    }

    fun getAnalyticsByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>> {
        return repository.getByDate(initialDate, finalDate)
    }
}