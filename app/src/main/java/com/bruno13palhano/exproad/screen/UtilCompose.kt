package com.bruno13palhano.exproad.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.R
import com.bruno13palhano.exproad.model.DailyActivity
import java.util.*

enum class Routes {
    EMAIL, ALARM, ANALYTICS,
    PROFILE, SETTINGS, HELP,
    EDIT_ACTIVITY_SCREEN, NEW_ACTIVITY_SCREEN,
    MAIN_SCREEN, DAILY_ACTIVITY_ID, MENU_ITEM
}

@Composable
fun DefaultAppBar(
    appBarTitle: String,
    onNavigateUp: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = appBarTitle) },
                navigationIcon = {
                    Button(
                        onClick = onNavigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button_description),
                        )
                    }
                }
            )
        },
        content = content
    )
}

@Composable
fun UserInput(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
    ),
    change: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = change,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MessageRow(
    dailyActivity: DailyActivity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp)
            .height(100.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(2.dp)
        ) {
            Text(
                text = dailyActivity.activityTitle,
                modifier = Modifier.padding(4.dp),
            )

            Text(
                text = dailyActivity.activityType,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
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
        onChange,
        year,
        month,
        day
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                mDatePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
        ) {
            Text(
                text = stringResource(id = R.string.date_label),
                color = Color.White
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
        onChange,
        hour,
        minute,
        true
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                mTimePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
        ) {
            Text(
                text = stringResource(id = R.string.time_label),
                color = Color.White
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(R.drawable.ic_baseline_access_time_24),
                contentDescription = stringResource(id = R.string.time_button_description)
            )
        }
    }
}