package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.dao.DailyActivityDao

internal class DailyActivityRepositoryDBImpl(
    private val dao: DailyActivityDao
) : DailyActivityRepository {
    override suspend fun addDailyActivity(dailyActivity: DailyActivity) {
        dao.insert(dailyActivity)
    }

    override suspend fun deleteDailyActivity(dailyActivity: DailyActivity) {
        dao.delete(dailyActivity)
    }

    override suspend fun updateDailyActivity(dailyActivity: DailyActivity) {
        dao.update(dailyActivity)
    }

    override fun get(activityId: Long): LiveData<DailyActivity> {
        return dao.get(activityId)
    }

    override fun getAll(): LiveData<List<DailyActivity>> {
        return dao.getAll()
    }

    override fun getDailyActivities(offset: Int, limit: Int): LiveData<List<DailyActivity>> {
        return dao.getActivitiesOrderByTitleAsc(offset, limit)
    }
}