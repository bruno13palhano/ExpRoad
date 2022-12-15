package com.bruno13palhano.activity_repository

import android.content.Context
import com.bruno13palhano.activity_repository.dao.DailyActivityDatabase

class DailyActivityRepositoryFactory(
    private val context: Context
) {
    private val dao = DailyActivityDatabase.getInstance(context).dailyActivityDao

    fun createDailyActivityRepositoryDB(): DailyActivityRepository {
        return DailyActivityRepositoryDBImpl(dao)
    }

    fun createDailyActivityAnalyticsRepositoryDB(): DailyActivityAnalyticsRepository {
        return DailyActivityAnalyticsRepositoryDBImpl(dao)
    }
}