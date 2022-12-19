package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.dao.DailyActivityAnalyticsDao
import com.bruno13palhano.activity_repository.model.asExternalModel
import java.util.*

internal class DailyActivityAnalyticsRepositoryDBImpl(
    private val dao: DailyActivityAnalyticsDao
) : DailyActivityAnalyticsRepository {

    override fun getByTitle(activityTitle: String): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getAnalyticsByTitle(activityTitle)
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyListImpls ->
                dailyListImpls.map { it.asExternalModel() }
            }

        return dailyList
    }

    override fun getByType(activityType: String): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getAnalyticsByType(activityType)
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyListImpls ->
                dailyListImpls.map { it.asExternalModel() }
            }

        return dailyList
    }

    override fun getByDescription(activityDescription: String): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getAnalyticsByDescription(activityDescription)
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyListImpls ->
                dailyListImpls.map { it.asExternalModel() }
            }

        return dailyList
    }

    override fun getByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getAnalyticsByDate(initialDate, finalDate)
        val dailyList:LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyListImpls ->
                dailyListImpls.map { it.asExternalModel() }
            }

        return dailyList
    }

    override fun getTop10ByHours(): LiveData<List<DailyActivity>> {
        val dailyListImpl = dao.getTop10ByHours()
        val dailyList: LiveData<List<DailyActivity>> =
            Transformations.map(dailyListImpl) { dailyActivityImpls ->
                dailyActivityImpls.map { it.asExternalModel() }
            }

        return dailyList
    }

}