package com.bruno13palhano.activity_repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bruno13palhano.activity_model.DailyActivity

@Dao
internal interface DailyActivityDao{

    @Insert
    suspend fun insert(dailyActivity: DailyActivity)

    @Update
    suspend fun update(dailyActivity: DailyActivity)

    @Delete
    suspend fun delete(dailyActivity: DailyActivity)

    @Query("SELECT * FROM daily_activity_table")
    fun getAll(): LiveData<List<DailyActivity>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_id = :activityId")
    fun get(activityId: Long): LiveData<DailyActivity>

    @Query("SELECT * FROM daily_activity_table ORDER BY activity_title ASC LIMIT :offset,:limit")
    fun getActivitiesOrderByTitleAsc(offset: Int, limit: Int): LiveData<List<DailyActivity>>
}