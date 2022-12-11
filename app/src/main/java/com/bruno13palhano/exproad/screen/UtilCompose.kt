package com.bruno13palhano.exproad.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.viewmodel.EditDailyActivityViewModel
import java.util.*

enum class Routes {
    EMAIL, ALARM, ANALYTICS,
    PROFILE, SETTINGS, HELP,
    EDITACTIVITYSCREEN, NEWACTIVITYSCREEN,
    MAINSCREEN
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
                            contentDescription = "Back",
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
    //Fetching the local Context
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    //Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        onChange,
        mYear,
        mMonth,
        mDay
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
            Text(text = "Date", color = Color.White)

            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(com.bruno13palhano.exproad.R.drawable.ic_baseline_calendar_today_24),
                contentDescription = "calendar"
            )
        }
    }
}

@Composable
fun TimePickerTest(
    onChange: (timePicker: TimePicker, hour: Int, minute: Int) -> Unit
) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        onChange,
        mHour,
        mMinute,
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
            Text(text = "Time", color = Color.White)

            Spacer(modifier = Modifier.padding(2.dp))

            Image(
                painter = painterResource(com.bruno13palhano.exproad.R.drawable.ic_baseline_access_time_24),
                contentDescription = "clock"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DrawerContent() {
    LazyColumn(

    ) {
        item {
            Text(
                modifier = Modifier.padding(4.dp),
                text = "Email"
            )
        }
    }
}