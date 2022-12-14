package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.activity_model.DailyActivity

interface DailyActivityRepository {
    suspend fun addDailyActivity(dailyActivity: DailyActivity)
    suspend fun deleteDailyActivity(dailyActivity: DailyActivity)
    suspend fun updateDailyActivity(dailyActivity: DailyActivity)
    fun get(activityId: Long): LiveData<DailyActivity>
    fun getAll(): LiveData<List<DailyActivity>>
    fun getDailyActivities(offset: Int, limit: Int): LiveData<List<DailyActivity>>
}