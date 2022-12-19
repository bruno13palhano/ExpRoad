package com.bruno13palhano.activity_repository.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bruno13palhano.activity_repository.model.DailyActivityImpl
import java.util.*

@Dao
internal interface DailyActivityDao{

    @Insert
    suspend fun insert(dailyActivity: DailyActivityImpl)

    @Update
    suspend fun update(dailyActivity: DailyActivityImpl)

    @Delete
    suspend fun delete(dailyActivity: DailyActivityImpl)

    @Query("SELECT * FROM daily_activity_table")
    fun getAll(): LiveData<List<DailyActivityImpl>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_id = :activityId")
    fun get(activityId: Long): LiveData<DailyActivityImpl>

    @Query("SELECT * FROM daily_activity_table ORDER BY activity_title ASC LIMIT :offset,:limit")
    fun getActivitiesOrderByTitleAsc(offset: Int, limit: Int): LiveData<List<DailyActivityImpl>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_title LIKE '%' || :activityTitle || '%'")
    fun getByTitle(activityTitle: String): LiveData<List<DailyActivityImpl>>

    @Query("SELECT * FROM daily_activity_table WHERE activity_date BETWEEN :initialDate AND :finalDate")
    fun getByDate(initialDate: Date, finalDate: Date): LiveData<List<DailyActivityImpl>>
}