package com.bruno13palhano.exproad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.repository.DailyActivityRepository
import kotlinx.coroutines.launch
import java.util.*

class EditDailyActivityViewModel(
    private val repository: DailyActivityRepository
) : ViewModel() {

    var dateFormatted: String = ""

    fun getDailyActivity(dailyActivityId: Long): LiveData<DailyActivity> {
        return repository.get(dailyActivityId)
    }

    fun updateDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.addDailyActivity(dailyActivity)
        }
    }

    fun updateDailyActivityTitle(newTitle: String, activityId: Long) {
        viewModelScope.launch {
            repository.updateDailyActivityTitle(newTitle, activityId)
        }
    }

    fun updateDailyActivityType(newType: String, activityId: Long) {
        viewModelScope.launch {
            repository.updateDailyActivityType(newType, activityId)
        }
    }

    fun updateDailyActivityDescription(newDescription: String, activityId: Long) {
        viewModelScope.launch {
            repository.updateDailyActivityDescription(newDescription, activityId)
        }
    }

    fun updateDailyActivityTime(hour: Int, minute: Int, activityId: Long) {
        val calendar = getCalendarTime(hour, minute)
        viewModelScope.launch {
            repository.updateDailyActivityTime(calendar.timeInMillis, activityId)
        }
    }

    fun updateDailyActivityDate(day: Int, month: Int, year: Int, activityId: Long) {
        val calendar = getCalendarDate(day, month, year)
        viewModelScope.launch {
            repository.updateDailyActivityDate(calendar.timeInMillis, activityId)
        }
    }

    private fun getCalendarTime(hour: Int, minute: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute

        return calendar
    }

    private fun getCalendarDate(day: Int, month: Int, year: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_MONTH] = day
        calendar[Calendar.MONTH] = month
        calendar[Calendar.YEAR] = year

        return calendar
    }

    fun formatTime(time: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time

        return "${calendar[Calendar.HOUR_OF_DAY]}:" +
                "${calendar[Calendar.MINUTE]}"
    }

    fun formatDate(date: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date

        return "${calendar[Calendar.DAY_OF_MONTH]}/" +
                "${calendar[Calendar.MONTH]}/" +
                "${calendar[Calendar.YEAR]}"
    }
}