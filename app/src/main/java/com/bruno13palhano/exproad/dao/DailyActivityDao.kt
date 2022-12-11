package com.bruno13palhano.exproad.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bruno13palhano.exproad.model.DailyActivity

@Dao
interface DailyActivityDao {

    @Insert
    suspend fun insert(dailyActivity: DailyActivity)

    @Update
    suspend fun update(dailyActivity: DailyActivity)

    @Delete
    suspend fun delete(dailyActivity: DailyActivity)

    @Query("SELECT * FROM daily_activity_table")
    fun getAll() : LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_id = :activityId")
    fun get(activityId: Long) : LiveData<DailyActivity>

    @Query("SELECT * FROM daily_activity_table ORDER BY activity_title ASC LIMIT :startAt,:activitiesPerQuery")
    fun getActivitiesOrderByTitlesAsc(startAt: Int, activitiesPerQuery: Int) : LiveData<List<DailyActivity>>

    @Query("UPDATE daily_activity_table SET activity_title = :newTitle WHERE activity_id = :id")
    suspend fun updateDailyActivityTitle(newTitle: String, id: Long)

    @Query("UPDATE daily_activity_table SET activity_type = :newType WHERE activity_id = :id")
    suspend fun updateDailyActivityType(newType: String, id: Long)

    @Query("UPDATE daily_activity_table SET activity_description = :newDescription WHERE activity_id = :id")
    suspend fun updateDailyActivityDescription(newDescription: String, id: Long)

    @Query("UPDATE daily_activity_table SET activity_time = :newTime WHERE activity_id = :id")
    suspend fun updateDailyActivityTime(newTime: Long, id: Long)

    @Query("UPDATE daily_activity_table SET activity_date = :newDate WHERE activity_id = :id")
    suspend fun updateDailyActivityDate(newDate: Long, id: Long)
}