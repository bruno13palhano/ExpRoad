package com.bruno13palhano.activity_model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "daily_activity_table")
data class DailyActivity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "activity_id")
    var activityId: Long = 0L,

    @ColumnInfo(name = "activity_title")
    var activityTitle: String,

    @ColumnInfo(name = "activity_type")
    var activityType: String,

    @ColumnInfo(name = "activity_description")
    var activityDescription: String = "",

    @ColumnInfo(name = "activity_time")
    var activityTime: Long = 10800000L,

    @ColumnInfo(name = "activity_date")
    var activityDate: Date = Date(),

)
