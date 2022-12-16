package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.dao.DailyActivityAnalyticsDao
import com.bruno13palhano.activity_repository.dao.DailyActivityDao
import java.util.*

internal class DailyActivityAnalyticsRepositoryDBImpl(
    private val dao: DailyActivityAnalyticsDao
) : DailyActivityAnalyticsRepository {

    override fun getByTitle(activityTitle: String): LiveData<List<DailyActivity>> {
        return dao.getAnalyticsByTitle(activityTitle)
    }

    override fun getByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>> {
        return dao.getAnalyticsByDate(initialDate, finalDate)
    }
}