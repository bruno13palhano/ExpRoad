package com.bruno13palhano.exproad.screen

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStoreOwner
import com.bruno13palhano.exproad.viewmodel.DailyActivityViewModelFactory

@Composable
fun DailyActivityAnalyticsScreen(
    context: Context,
    owner: ViewModelStoreOwner,
    onNavigateUp: () -> Unit,
) {

    val text = remember { mutableStateOf("Non fuit in solo Roma peracta die.") }
    val viewModel = DailyActivityViewModelFactory(context, owner)
        .createDailyActivityAnalyticsViewModel()

    viewModel.getAnalyticsByTitle("test").observeAsState().value?.forEach {
        text.value = it.activityTitle
    }

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

@Preview(showBackground = true)
@Composable
fun CanvasPreview() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        val canvasQuadrantSize = Size(canvasWidth, canvasHeight / 4)
        inset(
            vertical = (canvasHeight - (canvasHeight / 4)) / 2
        ) {
            drawRect(
                color = Color.Green,
                size = canvasQuadrantSize
            )
        }

        val fillPath = android.graphics.Path()
            .asComposePath()
            .apply {
                lineTo(0f, canvasHeight / 2)
                lineTo(canvasWidth / 2, (canvasHeight - (canvasHeight / 4)) / 2)
                lineTo(canvasWidth, canvasHeight / 2)
                lineTo(canvasWidth / 2, (canvasHeight + (canvasHeight / 4)) / 2)
                lineTo(0f, canvasHeight / 2)
                close()
            }

        drawPath(
            fillPath,
            color = Color.Yellow
        )

        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 6
        )

        val ordemSize = Size((size.minDimension / 3), (size.minDimension / 18))
        inset(
            vertical = (canvasHeight - (size.minDimension / 18)) / 2,
            horizontal = (canvasWidth - (size.minDimension / 3)) / 2
        ) {
            drawRect(
                color = Color.White,
                size = ordemSize
            )
        }
    }
}