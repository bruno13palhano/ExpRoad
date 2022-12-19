package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.dao.DailyActivityDao
import com.bruno13palhano.activity_repository.model.asExternalModel
import com.bruno13palhano.activity_repository.util.convertToDailyActivityImpl

internal class DailyActivityRepositoryDBImpl(
    private val dao: DailyActivityDao
) : DailyActivityRepository {
    override suspend fun addDailyActivity(dailyActivity: DailyActivity) {
        dao.insert(convertToDailyActivityImpl(dailyActivity))
    }

    override suspend fun deleteDailyActivity(dailyActivity: DailyActivity) {
        dao.delete(convertToDailyActivityImpl(dailyActivity))
    }

    override suspend fun updateDailyActivity(dailyActivity: DailyActivity) {
        dao.update(convertToDailyActivityImpl(dailyActivity))
    }

    override fun get(activityId: Long): LiveData<DailyActivity> {
        val dailyImpl = dao.get(activityId)
        val dailyActivity: LiveData<DailyActivity> =
            Transformations.map(dailyImpl) {
                it.asExternalModel()
            }

        return dailyActivity
    }

    override fun getAll(): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getAll()
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyListImpls ->
                dailyListImpls.map {
                    it.asExternalModel()
                }
            }

        return dailyList
    }

    override fun getDailyActivities(offset: Int, limit: Int): LiveData<List<DailyActivity>> {
        val dailListImpl = dao.getActivitiesOrderByTitleAsc(offset, limit)
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailListImpl) { dailListImpls ->
                dailListImpls.map {
                    it.asExternalModel()
                }
            }

        return dailyList
    }
}