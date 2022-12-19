package com.bruno13palhano.activity_repository.util

import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_repository.model.DailyActivityImpl

fun convertToDailyActivityImpl(dailyActivity: DailyActivity): DailyActivityImpl {
    return DailyActivityImpl(
        activityId = dailyActivity.activityId,
        activityTitle = dailyActivity.activityTitle,
        activityType = dailyActivity.activityType,
        activityDescription = dailyActivity.activityDescription,
        activityTime = dailyActivity.activityTime,
        activityDate = dailyActivity.activityDate
    )
}