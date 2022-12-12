package com.bruno13palhano.exproad.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.R
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.viewmodel.NewDailyActivityScreenViewModel


@Composable
fun NewDailyActivityScreen(
    viewModel: NewDailyActivityScreenViewModel,
    onNavigateUp: () -> Unit,
    onNavigateToMainScreen: () -> Unit
) {
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
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                UserInput(
                    value = titleValue.value,
                    label = stringResource(id = R.string.input_title_label)
                ) {
                    titleValue.value = it
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = typeValue.value,
                    label = stringResource(id = R.string.input_type_label)
                ) {
                    typeValue.value = it
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = descriptionValue.value,
                    label = stringResource(id = R.string.input_description_label)
                ) {
                    descriptionValue.value = it
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = timeValue.value,
                    label = stringResource(id = R.string.input_time_label),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                ) {
                    timeValue.value = it
                }

                Spacer(modifier = Modifier.padding(2.dp))

                UserInput(
                    value = dateValue.value,
                    label = stringResource(id = R.string.input_date_label),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                ) {
                    dateValue.value = it
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
                            viewModel.addDailyActivity(DailyActivity(
                                activityTitle = titleValue.value,
                                activityType = typeValue.value,
                                activityDescription = descriptionValue.value,
                                //missing the others parameters
                            ))
                            onNavigateToMainScreen.invoke()
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Icon(
                            Icons.Filled.Done,
                            stringResource(id = R.string.done_button_description)
                        )
                    }
                }
            }
        }
    }
}

