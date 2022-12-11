package com.bruno13palhano.exproad.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.R
import com.bruno13palhano.exproad.model.DailyActivity
import com.bruno13palhano.exproad.ui.theme.ExpRoadTheme
import com.bruno13palhano.exproad.viewmodel.MainScreenViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val list = listOf<DailyActivity>(
        DailyActivity(
            activityTitle = "Head First Java",
            activityType = "Programming Language"
        ),
        DailyActivity(
            activityTitle = "Head First Kotlin",
            activityType = "Programming Language"
        ),
        DailyActivity(
            activityTitle = "Head First Android",
            activityType = "Programming Language"
        ),
        DailyActivity(
            activityTitle = "Head First SQL",
            activityType = "Programming Language"
        ),
        DailyActivity(
            activityTitle = "Android Compose",
            activityType = "Android Development"
        ),
        DailyActivity(
            activityTitle = "Live Data and Room Database",
            activityType = "Android Development"
        ),
        DailyActivity(
            activityTitle = "Clean Architecture",
            activityType = "Software Architecture"
        ),
        DailyActivity(
            activityTitle = "Design Patterns with Java",
            activityType = "Software Architecture"
        ),
    )

    ExpRoadTheme {
        Surface {
            MainScreenContent(list, {})
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    onItemClick: (String) -> Unit,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit,
    onNavigateToNewDailyActivityScreen: () -> Unit

) {
    val dailyActivityList = viewModel.getAll()

    dailyActivityList.observeAsState().let {
        it.value?.let { dailyList ->
            MainDrawerScreen(
                dailyActivities = dailyList,
                onItemClick = onItemClick,
                onNavigateToEditDailyActivityScreen = onNavigateToEditDailyActivityScreen,
                onNavigateToNewDailyActivityScreen = onNavigateToNewDailyActivityScreen
            )
        }
    }
}

@Composable
fun MainDrawerScreen(
    dailyActivities: List<DailyActivity>,
    onItemClick: (String) -> Unit,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit,
    onNavigateToNewDailyActivityScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(
            title = { Text("ExpRoad") },
            navigationIcon = {
                Button(
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState
                                .apply {
                                    if (isClosed) open() else close()
                                }
                        }
                    }
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        "Open close menu"
                    )
                }
            }
        )},
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToNewDailyActivityScreen,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Add new daily activity"
                )
            }
        },
        drawerContent = {
            DrawerMainContent(
                onItemClick = onItemClick
            )
        }
    ) { paddingValues ->

        Spacer(modifier = Modifier.padding(paddingValues))
        MainScreenContent(
            dailyActivities = dailyActivities,
            onNavigateToEditDailyActivityScreen = onNavigateToEditDailyActivityScreen
        )
    }
}

@Composable
fun MainScreenContent(
    dailyActivities: List<DailyActivity>,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit
) {
    LazyColumn {
        items(dailyActivities) { dailyActivity ->
            MessageRow(
                dailyActivity = dailyActivity,
                onClick = {
                    onNavigateToEditDailyActivityScreen.invoke(dailyActivity.activityId)
                }
            )
        }
    }
}

@Composable
fun DrawerMainContent(
    onItemClick: (String) -> Unit
) {
    val itemList = prepareNavigationDrawerItems()
    val gradientColors = listOf(
        Color(MaterialTheme.colors.background.value),
        Color(MaterialTheme.colors.primary.value))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(gradientColors))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                ,
                painter = painterResource(id = R.drawable.path),
                contentDescription = "Profile Image",
            )
        }

        item {
            Text(text = "someone@gmail.com")
            Spacer(modifier = Modifier.padding(bottom = 72.dp))
        }

        items(itemList) { item ->
            NavigationDrawerItemList(
                menuItem = item,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun NavigationDrawerItemList(
    menuItem: MenuDrawerItem,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(menuItem.route)
            }
            .padding(
                horizontal = 24.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = menuItem.image,
            contentDescription = null
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = menuItem.label
        )
    }
}

@Composable
private fun prepareNavigationDrawerItems(): List<MenuDrawerItem> {
    val itemList = arrayListOf<MenuDrawerItem>()

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_email_24),
            label = "Email",
            route = "email",
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_alarm_24),
            label = "Alarm",
            route = "alarm"
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_analytics_24),
            label = "Analytics",
            route = "analytics"
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_person_24),
            label = "Profile",
            route = "profile"
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_settings_24),
            label = "Settings",
            route = "settings"
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_help_24),
            label = "Help",
            route = "help"
        )
    )

    return itemList
}

data class MenuDrawerItem(
    val image: Painter,
    val label: String,
    val route: String,
)

@Preview(showBackground = true)
@Composable
fun DrawerMainContentPreview() {
    val itemList = prepareNavigationDrawerItems()
    val gradientColors = listOf(
        Color(MaterialTheme.colors.background.value),
        Color(MaterialTheme.colors.primary.value))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(gradientColors))
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                ,
                painter = painterResource(id = R.drawable.path),
                contentDescription = "Profile Image",
            )
        }

        item {
            Text(text = "someone@gmail.com")
            Spacer(modifier = Modifier.padding(bottom = 72.dp))
        }

        items(itemList) { item ->
            NavigationDrawerItemList(
                menuItem = item,
                onItemClick = {}
            )
        }
    }
}