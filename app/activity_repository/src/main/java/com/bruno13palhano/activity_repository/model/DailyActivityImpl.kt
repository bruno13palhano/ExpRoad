package com.bruno13palhano.activity_repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bruno13palhano.activity_model.DailyActivity
import java.util.*

@Entity(tableName = "daily_activity_table")
data class DailyActivityImpl(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "activity_id")
    val activityId: Long,

    @ColumnInfo(name = "activity_title")
    val activityTitle: String,

    @ColumnInfo(name = "activity_type")
    val activityType: String,

    @ColumnInfo(name = "activity_description")
    val activityDescription: String,

    @ColumnInfo(name = "activity_time")
    val activityTime: Long = 10800000L,

    @ColumnInfo(name = "activity_date")
    val activityDate: Date = Date()
)

fun DailyActivityImpl.asExternalModel() = DailyActivity(
    activityId,
    activityTitle,
    activityType,
    activityDescription,
    activityTime,
    activityDate
)