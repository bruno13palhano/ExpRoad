package com.bruno13palhano.exproad.repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.exproad.model.DailyActivity

interface DailyActivityRepository {
    suspend fun addDailyActivity(dailyActivity: DailyActivity)
    suspend fun deleteDailyActivity(dailyActivity: DailyActivity)
    suspend fun updateDailyActivity(dailyActivity: DailyActivity)
    fun get(dailyActivityId: Long) : LiveData<DailyActivity>
    fun getDailyActivities(offSet: Int, limit: Int) : LiveData<List<DailyActivity>>
    fun getAll() : LiveData<List<DailyActivity>>
    suspend fun updateDailyActivityTitle(newTitle: String, id: Long)
    suspend fun updateDailyActivityType(newType: String, id: Long)
    suspend fun updateDailyActivityDescription(newDescription: String, id: Long)
    suspend fun updateDailyActivityTime(newTime: Long, id: Long)
    suspend fun updateDailyActivityDate(newDate: Long, id: Long)
}