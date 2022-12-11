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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.ui.theme.ExpRoadTheme
import com.bruno13palhano.exproad.viewmodel.EditDailyActivityViewModel

@Composable
fun EditDailyActivityScreen(
    dailyActivityId: Long,
    viewModel: EditDailyActivityViewModel,
    onNavigateToMainScreen: () -> Unit
) {
    ExpRoadTheme {
        Surface {
            EditDailyActivityContent(
                dailyActivityId = dailyActivityId,
                viewModel = viewModel,
                onNavigateToMainScreen = onNavigateToMainScreen
            )
        }
    }
}

@Composable
fun EditDailyActivityContent(
    dailyActivityId: Long,
    viewModel: EditDailyActivityViewModel,
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

    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        UserInput(
            value = titleValue.value,
            label = "New title"
        ) {
            titleValue.value = it
        }

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = typeValue.value,
            label = "New type"
        ) {
            typeValue.value = it
        }

        Spacer(modifier = Modifier.padding(2.dp))

        UserInput(
            value = descriptionValue.value,
            label = "New description"
        ) {
            descriptionValue.value = it
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
                label = "New time",
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
                label = "New date",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            ) {
                dateValue.value = it
            }
        }

        Row(
            modifier = Modifier
                .padding(8.dp)
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
                    contentDescription = "Action done"
                )
            }
        }
    }
}

