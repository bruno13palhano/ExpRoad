package com.bruno13palhano.activity_model

import java.util.*

data class DailyActivity(
    val activityId: Long = 0L,
    val activityTitle: String,
    val activityType: String,
    val activityDescription: String = "",
    val activityTime: Long = defaultTime,
    val activityDate: Date = Date(),
)