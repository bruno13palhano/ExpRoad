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

    fun getAnalyticsByType(activityType: String): LiveData<List<DailyActivity>> {
        return repository.getByType(activityType)
    }

    fun getAnalyticsByDescription(activityDescription: String): LiveData<List<DailyActivity>> {
        return repository.getByDescription(activityDescription)
    }

    fun getAnalyticsByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>> {
        return repository.getByDate(initialDate, finalDate)
    }

    fun getAnalyticsTop10ByHours(): LiveData<List<DailyActivity>> {
        return repository.getTop10ByHours()
    }

    fun getHour(timeInMillis: Long): Int {
        val time = Calendar.getInstance()
        time.timeInMillis = timeInMillis

        return time[Calendar.HOUR_OF_DAY]
    }

    fun getDay(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar[Calendar.DAY_OF_MONTH]
    }
}