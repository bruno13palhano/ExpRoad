package com.bruno13palhano.activity_repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.bruno13palhano.activity_model.DailyActivity
import java.util.*

@Dao
internal interface DailyActivityAnalyticsDao {

    @Query("SELECT * FROM daily_activity_table WHERE activity_title LIKE '%' || :activityTitle || '%'")
    fun getAnalyticsByTitle(activityTitle: String): LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_type LIKE '%' || :activityType || '%'")
    fun getAnalyticsByType(activityType: String): LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_description LIKE '%' || :activityDescription || '%'")
    fun getAnalyticsByDescription(activityDescription: String): LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_date BETWEEN :initialDate AND :finalDate")
    fun getAnalyticsByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table daily1 WHERE 9=(" +
            "SELECT COUNT(DISTINCT(daily2.activity_time)) FROM daily_activity_table daily2 WHERE " +
            "daily2.activity_time > daily1.activity_time)")
    fun getTop10Hours(): LiveData<List<DailyActivity>>
}