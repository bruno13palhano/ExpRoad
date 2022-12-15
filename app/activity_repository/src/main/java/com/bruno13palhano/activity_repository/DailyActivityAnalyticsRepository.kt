package com.bruno13palhano.activity_repository

import androidx.lifecycle.LiveData
import com.bruno13palhano.activity_model.DailyActivity
import java.util.*

interface DailyActivityAnalyticsRepository {
    fun getByTitle(activityTitle: String): LiveData<List<DailyActivity>>
    fun getByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>>
}