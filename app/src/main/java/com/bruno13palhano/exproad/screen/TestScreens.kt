package com.bruno13palhano.exproad.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bruno13palhano.exproad.R
import kotlinx.coroutines.launch

@Preview
@Composable
fun MainDrawerScreenTest() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            },
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
                        stringResource(id = R.string.drawer_menu_description)
                    )
                }
            },
        )},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.add_new_activity_button_description)
                )
            }
        },
        drawerContent = {
            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = "Test from drawerContent"
                )
            }
        },
    ) { paddingValues ->
        // Screen content
        Column(modifier = Modifier.padding(paddingValues)) {

        }
    }
}