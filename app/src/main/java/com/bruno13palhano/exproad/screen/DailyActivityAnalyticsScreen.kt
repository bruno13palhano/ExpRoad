package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.activity_repository.DailyActivityRepositoryFactory

@Composable
fun DailyActivityAnalyticsScreen(
    context: Context,
    owner: ViewModelStoreOwner,
    onNavigateUp: () -> Unit,
) {

    val viewModel = DailyActivityRepositoryFactory(context)
        .createDailyActivityAnalyticsRepositoryDB()

    val text = remember { mutableStateOf("") }

    DefaultAppBar(
        appBarTitle = "Analytics Test",
        onNavigateUp = onNavigateUp
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = text.value)
        }
    }
}