package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.exproad.R
import com.bruno13palhano.activity_model.DailyActivity
import com.bruno13palhano.exproad.viewmodel.DailyActivityViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    context: Context,
    owner: ViewModelStoreOwner,
    onItemClick: (String) -> Unit,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit,
    onNavigateToNewDailyActivityScreen: () -> Unit

) {
    val viewModel = DailyActivityViewModelFactory(context, owner).createMainViewModel()
    var dailyActivityList: List<DailyActivity> = remember { emptyList() }

    val dailyActivities = viewModel.getAll()

    dailyActivities.observeAsState().value?.let { dailyList ->
        dailyActivityList = dailyList
    }

    MainDrawerScreen(
        dailyActivities = dailyActivityList,
        onItemClick = onItemClick,
        onNavigateToEditDailyActivityScreen = onNavigateToEditDailyActivityScreen,
        onNavigateToNewDailyActivityScreen = onNavigateToNewDailyActivityScreen
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawerScreen(
    dailyActivities: List<DailyActivity>,
    onItemClick: (String) -> Unit,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit,
    onNavigateToNewDailyActivityScreen: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    DrawerMainContentMD3(
        dailyActivities = dailyActivities,
        onItemClick = onItemClick,
        onNavigateToEditDailyActivityScreen = onNavigateToEditDailyActivityScreen,
        onNavigateToNewDailyActivityScreen = onNavigateToNewDailyActivityScreen,
        drawerState = drawerState,
        scope = scope,
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun MainScreenContent(
    dailyActivities: List<DailyActivity>,
    paddingValues: PaddingValues,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(
            count = dailyActivities.size,
            key = {
                dailyActivities[it].activityId
            },
            itemContent = { index ->
                val dailyActivity = dailyActivities[index]
                MessageRow(
                    dailyActivity = dailyActivity,
                    onClick = {
                        onNavigateToEditDailyActivityScreen.invoke(dailyActivity.activityId)
                    }
                )
            }
        )
    }
}

@Composable
fun DrawerMainContent(
    onItemClick: (String) -> Unit
) {
    val itemList = prepareNavigationDrawerItems()
    val gradientColors = listOf(
        Color(Color.White.value),
        Color(androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer.value))

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
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.profile_picture),
                contentDescription = stringResource(id = R.string.profile_image_description),
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.email_description)
            )
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
            label = stringResource(id = R.string.email_label),
            route = Routes.EMAIL.name,
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_alarm_24),
            label = stringResource(id = R.string.alarm_label),
            route = Routes.ALARM.name
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_analytics_24),
            label = stringResource(id = R.string.analytics_label),
            route = Routes.ANALYTICS.name
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_person_24),
            label = stringResource(id = R.string.profile_label),
            route = Routes.PROFILE.name
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_settings_24),
            label = stringResource(id = R.string.settings_label),
            route = Routes.SETTINGS.name
        )
    )

    itemList.add(
        MenuDrawerItem(
            image = painterResource(id = R.drawable.ic_baseline_help_24),
            label = stringResource(id = R.string.help_label),
            route = Routes.HELP.name
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
        Color(MaterialTheme.colorScheme.background.value),
        Color(MaterialTheme.colorScheme.primary.value))

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
                painter = painterResource(id = R.drawable.profile_picture),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMainContentMD3(
    dailyActivities: List<DailyActivity>,
    onItemClick: (String) -> Unit,
    onNavigateToEditDailyActivityScreen: (dailyActivityId: Long) -> Unit,
    onNavigateToNewDailyActivityScreen: () -> Unit,
    drawerState: DrawerState,
    scope: CoroutineScope,
    scrollBehavior: TopAppBarScrollBehavior
) {
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMainContent {
                    onItemClick(it)
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                LargeTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            fontSize = 28.sp,
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        androidx.compose.material3.IconButton(
                            onClick = {
                                scope.launch {
                                    if(drawerState.isOpen) {
                                        drawerState.close()
                                    } else {
                                        drawerState.open()
                                    }
                                }
                            }
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = stringResource(id = R.string.drawer_menu_description)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButton =  {
                androidx.compose.material3.ExtendedFloatingActionButton(
                    onClick = onNavigateToNewDailyActivityScreen,
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        stringResource(id = R.string.add_new_activity_button_description)
                    )
                }
            }

        ) { paddingValues ->
            MainScreenContent(
                dailyActivities = dailyActivities,
                paddingValues = paddingValues,
                onNavigateToEditDailyActivityScreen = onNavigateToEditDailyActivityScreen
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DrawerTest() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// icons to mimic drawer destinations
    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { androidx.compose.material3.Icon(item, contentDescription = null) },
                        label = { androidx.compose.material3.Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                Spacer(Modifier.height(20.dp))
                androidx.compose.material3.Button(onClick = { scope.launch { drawerState.open() } }) {
                    androidx.compose.material3.Text("Click to open")
                }
            }
        }
    )
}