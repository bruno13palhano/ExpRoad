package com.bruno13palhano.exproad.screen

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
import com.bruno13palhano.exproad.R
import com.bruno13palhano.exproad.viewmodel.EditDailyActivityViewModel

@Composable
fun EditDailyActivityScreen(
    dailyActivityId: Long,
    viewModel: EditDailyActivityViewModel,
    onNavigateUp: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
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
        dateValue.value = it.value?.activityDate?.time?.let { date ->
            viewModel.formatDate(date)
        }.toString()
    }

    DefaultAppBar(
        appBarTitle = stringResource(id = R.string.edit_activity_screen),
        onNavigateUp = onNavigateUp
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                UserInput(
                    value = titleValue.value,
                    label = stringResource(id = R.string.input_new_title_label)
                ) {
                    titleValue.value = it
                    viewModel.updateDailyActivityTitle(it, dailyActivityId)
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = typeValue.value,
                    label = stringResource(id = R.string.input_new_type_label)
                ) {
                    typeValue.value = it
                    viewModel.updateDailyActivityType(it, dailyActivityId)
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = descriptionValue.value,
                    label = stringResource(id = R.string.input_new_description)
                ) {
                    descriptionValue.value = it
                    viewModel.updateDailyActivityDescription(it, dailyActivityId)
                }

                Spacer(modifier = Modifier.padding(2.dp))

                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TimePickerTest { _, hour, minute ->
                        viewModel.updateDailyActivityTime(hour, minute, dailyActivityId)
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    UserInput(
                        value = timeValue.value,
                        label = stringResource(id = R.string.input_new_time_label),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    ) {
                        timeValue.value = it
                    }
                }

                Spacer(modifier = Modifier.padding(2.dp))

                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DatePickerTest { _, year, month, day ->
                        viewModel.updateDailyActivityDate(day, month, year, dailyActivityId)
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    UserInput(
                        value = dateValue.value,
                        label = stringResource(id = R.string.input_new_date_label),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    ) {
                        dateValue.value = it
                    }
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
                        onClick = onNavigateToMainScreen,
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
    }
}

