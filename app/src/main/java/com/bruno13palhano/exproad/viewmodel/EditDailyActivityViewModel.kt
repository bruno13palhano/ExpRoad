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

    fun getDailyActivity(dailyActivityId: Long): LiveData<DailyActivity> {
        return repository.get(dailyActivityId)
    }

    fun updateDailyActivity(dailyActivity: DailyActivity) {
        viewModelScope.launch {
            repository.updateDailyActivity(dailyActivity)
        }
    }

    fun getCalendarTime(hour: Int, minute: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] = minute

        return calendar.timeInMillis
    }

    fun getCalendarDate(day: Int, month: Int, year: Int): Date {
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_MONTH] = day
        calendar[Calendar.MONTH] = month
        calendar[Calendar.YEAR] = year

        return calendar.time
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