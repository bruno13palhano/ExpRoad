package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.exproad.R
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.exproad.viewmodel.DailyActivityViewModelFactory
import java.util.*

@Composable
fun EditDailyActivityScreen(
    dailyActivityId: Long,
    context: Context,
    owner: ViewModelStoreOwner,
    onNavigateUp: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {

    val viewModel = DailyActivityViewModelFactory(context, owner).createEditDailyActivityViewModel()

    var time: Long
    var date: Date

    val titleValue = remember { mutableStateOf("") }
    val typeValue = remember { mutableStateOf("") }
    val descriptionValue = remember { mutableStateOf("") }
    val timeValue = remember { mutableStateOf("") }
    val dateValue = remember { mutableStateOf("") }

    val currentDailyActivity = viewModel.getDailyActivity(dailyActivityId).observeAsState()
    currentDailyActivity.let {
        titleValue.value = it.value?.activityTitle ?: ""
        typeValue.value = it.value?.activityType ?: ""
        descriptionValue.value = it.value?.activityDescription ?: ""
        timeValue.value = it.value?.activityTime?.let { time ->
            viewModel.formatTime(time)
        }.toString()
        dateValue.value = it.value?.activityDate?.let { date ->
            viewModel.formatDate(date)
        }.toString()

        time = it.value?.activityTime ?: 0
        date = it.value?.activityDate ?: Date()
    }

    DefaultAppBar(
        appBarTitle = stringResource(id = R.string.edit_activity_screen),
        onNavigateUp = onNavigateUp
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            EditDailyActivityContent(
                activityId = dailyActivityId,
                activityTitle = titleValue.value,
                activityType = typeValue.value,
                activityDescription = descriptionValue.value,
                activityTime = timeValue.value,
                activityDate = dateValue.value,
                time = time,
                date = date,
                onActivityTitleChange = {
                    titleValue.value = it
                },
                onActivityTypeChange = {
                    typeValue.value = it
                },
                onActivityDescriptionChange = {
                    descriptionValue.value = it
                },
                onActivityTimeChange = {
                    timeValue.value = it
                },
                onActivityDateChange = {
                    dateValue.value = it
                },
                onTimeChange = { hour, minute ->
                    time = viewModel.getCalendarTime(hour, minute)
                    timeValue.value = viewModel.formatTime(time)
                },
                onDateChange = { day, month, year ->
                    date = viewModel.getCalendarDate(day, month, year)
                    dateValue.value = viewModel.formatDate(date)
                },
                onUpdateActivity = {
                    viewModel.updateDailyActivity(it)
                },
                onNavigateToMainScreen = onNavigateToMainScreen
            )
        }
    }
}

@Composable
fun EditDailyActivityContent(
    activityId: Long,
    activityTitle: String,
    activityType: String,
    activityDescription: String,
    activityTime: String,
    activityDate: String,
    time: Long,
    date: Date,
    onActivityTitleChange: (activityTitle: String) -> Unit,
    onActivityTypeChange: (activityType: String) -> Unit,
    onActivityDescriptionChange: (activityDescription: String) -> Unit,
    onActivityTimeChange: (activityTime: String) -> Unit,
    onActivityDateChange: (activityDate: String) -> Unit,
    onTimeChange: (hour: Int, minute: Int) -> Unit,
    onDateChange: (day: Int, month: Int, year: Int) -> Unit,
    onUpdateActivity: (dailyActivity: DailyActivity) -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        UserInput(
            value = activityTitle,
            label = stringResource(id = R.string.input_new_title_label),
            change = onActivityTitleChange
        )

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = activityType,
            label = stringResource(id = R.string.input_new_type_label),
            change = onActivityTypeChange
        )

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = activityDescription,
            label = stringResource(id = R.string.input_new_description),
            change = onActivityDescriptionChange
        )

        Spacer(modifier = Modifier.padding(2.dp))

        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TimePickerTest { _, hour, minute ->
                onTimeChange(hour, minute)
            }

            Spacer(modifier = Modifier.padding(4.dp))

            UserInput(
                value = activityTime,
                label = stringResource(id = R.string.input_new_time_label),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enable = false,
                change = onActivityTimeChange
            )
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            DatePickerTest { _, year, month, day ->
                onDateChange(day, month, year)
            }

            Spacer(modifier = Modifier.padding(4.dp))

            UserInput(
                value = activityDate,
                label = stringResource(id = R.string.input_new_date_label),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                enable = false,
                change = onActivityDateChange
            )
        }

        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            FloatingActionButton(
                onClick = {
                    onUpdateActivity(
                        DailyActivity(
                            activityId = activityId,
                            activityTitle = activityTitle,
                            activityType = activityType,
                            activityDescription = activityDescription,
                            activityTime = time,
                            activityDate = date
                        )
                    )
                    onNavigateToMainScreen()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = stringResource(id = R.string.done_button_description)
                )
            }
        }
    }
}

