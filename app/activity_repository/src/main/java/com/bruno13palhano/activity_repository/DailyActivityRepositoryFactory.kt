package com.bruno13palhano.activity_repository

import android.content.Context
import com.bruno13palhano.activity_repository.dao.DailyActivityDatabase

class DailyActivityRepositoryFactory(
    private val context: Context
) {
    fun createDailyActivityRepositoryDB(): DailyActivityRepository {
        val dao = DailyActivityDatabase.getInstance(context).dailyActivityDao
        return DailyActivityRepositoryDBImpl(dao)
    }
}