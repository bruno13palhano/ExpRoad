package com.bruno13palhano.exproad.repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.exproad.dao.DailyActivityDao
import com.bruno13palhano.exproad.model.DailyActivity

class DailyActivityRepositoryImpl(
    private val dao: DailyActivityDao
) : DailyActivityRepository{

    override suspend fun addDailyActivity(dailyActivity: DailyActivity) {
        dao.insert(dailyActivity)
    }

    override suspend fun deleteDailyActivity(dailyActivity: DailyActivity) {
        dao.delete(dailyActivity)
    }

    override suspend fun updateDailyActivity(dailyActivity: DailyActivity) {
        dao.update(dailyActivity)
    }

    override fun get(dailyActivityId: Long): LiveData<DailyActivity> {
        return dao.get(dailyActivityId)
    }

    override fun getDailyActivities(offSet: Int, limit: Int): LiveData<List<DailyActivity>> {
        return dao.getActivitiesOrderByTitlesAsc(offSet, limit)
    }

    override fun getAll(): LiveData<List<DailyActivity>> {
        return dao.getAll()
    }

    override suspend fun updateDailyActivityTitle(newTitle: String, id: Long) {
        dao.updateDailyActivityTitle(newTitle, id)
    }

    override suspend fun updateDailyActivityType(newType: String, id: Long) {
        dao.updateDailyActivityType(newType, id)
    }

    override suspend fun updateDailyActivityDescription(newDescription: String, id: Long) {
        dao.updateDailyActivityDescription(newDescription, id)
    }

    override suspend fun updateDailyActivityTime(newTime: Long, id: Long) {
        dao.updateDailyActivityTime(newTime, id)
    }

    override suspend fun updateDailyActivityDate(newDate: Long, id: Long) {
        dao.updateDailyActivityDate(newDate, id)
    }
}