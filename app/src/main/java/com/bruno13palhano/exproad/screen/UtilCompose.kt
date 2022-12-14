package com.bruno13palhano.exproad.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.R
import com.bruno13palhano.activity_model.DailyActivity
import java.util.*

enum class Routes {
    EMAIL, ALARM, ANALYTICS,
    PROFILE, SETTINGS, HELP,
    EDIT_ACTIVITY_SCREEN, NEW_ACTIVITY_SCREEN,
    MAIN_SCREEN, DAILY_ACTIVITY_ID, MENU_ITEM
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultAppBar(
    appBarTitle: String,
    onNavigateUp: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = appBarTitle)},
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInput(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    ),
    enable: Boolean = true,
    change: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = change,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        enabled = enable,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onPrimary,
            containerColor = MaterialTheme.colorScheme.background,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
            disabledIndicatorColor = MaterialTheme.colorScheme.primary,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
            disabledPlaceholderColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageRow(
    dailyActivity: DailyActivity,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp)
            .height(100.dp),
        elevation = CardDefaults.outlinedCardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(96.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    modifier = Modifier.padding(
                        top = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp
                    ),
                    painter = painterResource(id = R.drawable.ic_baseline_title_24),
                    contentDescription = "test",
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
                Text(
                    text = dailyActivity.activityTitle,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Justify
                )
            }
                Divider()
            Row(
                modifier = Modifier.height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(
                        top = 4.dp,
                        end = 4.dp,
                        bottom = 4.dp,
                        start = 8.dp
                    ),
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_right_alt_24),
                    contentDescription = "test",
                    tint = MaterialTheme.colorScheme.primaryContainer
                )

                Text(
                    text = dailyActivity.activityType,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardTest() {
    MessageRow(
        dailyActivity = DailyActivity(
            activityTitle = "Java",
            activityType = "Programming language"
        ),
        onClick = {}
    )
}

@Composable
fun DatePickerTest(
    onChange: (datePicker: DatePicker, year: Int, month: Int, day: Int) -> Unit
) {
    val context = LocalContext.current

    val year: Int
    val month: Int
    val day: Int

    val mCalendar = Calendar.getInstance()

    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        context,
        R.style.DialogTheme,
        onChange,
        year,
        month,
        day
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = {
                mDatePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = stringResource(id = R.string.date_label),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(R.drawable.ic_baseline_calendar_today_24),
                contentDescription = stringResource(id = R.string.date_button_description)
            )
        }
    }
}

@Composable
fun TimePickerTest(
    onChange: (timePicker: TimePicker, hour: Int, minute: Int) -> Unit
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val mTimePickerDialog = TimePickerDialog(
        context,
        R.style.DialogTheme,
        onChange,
        hour,
        minute,
        true
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedButton(
            onClick = {
                mTimePickerDialog.show()
            },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = stringResource(id = R.string.time_label),
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(R.drawable.ic_baseline_access_time_24),
                contentDescription = stringResource(id = R.string.time_button_description)
            )
        }
    }
}