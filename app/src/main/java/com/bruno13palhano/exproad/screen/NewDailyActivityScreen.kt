package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.exproad.R
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.activity_model.defaultTime
import com.bruno13palhano.exproad.viewmodel.DailyActivityViewModelFactory
import java.util.*


@Composable
fun NewDailyActivityScreen(
    context: Context,
    owner: ViewModelStoreOwner,
    onNavigateUp: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {

    val viewModel = DailyActivityViewModelFactory(context, owner).createNewDailyActivityViewModel()

    var time = defaultTime
    var date = Date()

    val titleValue = remember { mutableStateOf("") }
    val typeValue = remember { mutableStateOf("") }
    val descriptionValue = remember { mutableStateOf("") }
    val timeValue = remember { mutableStateOf("") }
    val dateValue = remember { mutableStateOf("") }

    DefaultAppBar(
        appBarTitle = stringResource(id = R.string.new_activity_screen),
        onNavigateUp = onNavigateUp
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            NewDailyActivityContent(
                activityTitle = titleValue.value,
                activityType = typeValue.value,
                activityDescription = descriptionValue.value,
                activityTime = timeValue.value,
                activityDate = dateValue.value,
                time = time,
                date = date,
                onActivityTitleChange = { titleValue.value = it },
                onActivityTypeChange = { typeValue.value = it },
                onActivityDescriptionChange = { descriptionValue.value = it },
                onActivityTimeChange = { timeValue.value = it },
                onActivityDateChange = { dateValue.value = it },
                onTimeChange = { hour, minute ->
                    time = viewModel.timeStringToLong(hour, minute)
                    timeValue.value = viewModel.formatTime(time)
                },
                onDateChange = { day, month, year ->
                    date = viewModel.dateStringToDate(day, month, year)
                    dateValue.value = viewModel.formatDate(date)
                },
                onAddActivity = {
                    viewModel.addDailyActivity(it)
                },
                onNavigateToMainScreen = onNavigateToMainScreen
            )
        }
    }
}

@Composable
fun NewDailyActivityContent(
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
    onAddActivity: (dailyActivity: DailyActivity) -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        UserInput(
            value = activityTitle,
            label = stringResource(id = R.string.input_title_label),
            change = onActivityTitleChange
        )

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = activityType,
            label = stringResource(id = R.string.input_type_label),
            change = onActivityTypeChange
        )

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = activityDescription,
            label = stringResource(id = R.string.input_description_label),
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
                label = stringResource(id = R.string.input_time_label),
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
                label = stringResource(id = R.string.input_date_label),
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
            ExtendedFloatingActionButton(
                onClick = {
                    onAddActivity(
                        DailyActivity(
                            activityTitle = activityTitle,
                            activityType = activityType,
                            activityDescription = activityDescription,
                            activityTime = time,
                            activityDate = date
                        )
                    )
                    onNavigateToMainScreen.invoke()
                },
                shape = RoundedCornerShape(16.dp),
                elevation = FloatingActionButtonDefaults.elevation(),
            ) {
                Icon(
                    Icons.Filled.Done,
                    stringResource(id = R.string.done_button_description)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    val test = "Test"
    NewDailyActivityContent(
        activityTitle = test,
        activityType = test,
        activityDescription = test,
        activityTime = test,
        activityDate = test,
        time = 0L,
        date = Date(),
        onActivityTitleChange = {},
        onActivityTypeChange = {},
        onActivityDescriptionChange = {},
        onActivityTimeChange = {},
        onActivityDateChange = {},
        onTimeChange = { hour, time ->

        },
        onDateChange = { day, month, year ->

        },
        onAddActivity = {}
    ) {

    }
}