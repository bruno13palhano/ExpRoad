package com.bruno13palhano.exproad.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = SkyBlue,
    primaryContainer = SkyBlue,
    secondary = Teal200,
    background = LightBrown,
    onBackground = Black,
    onPrimary = White
)

private val LightColorPalette = lightColorScheme(
    primary = SkyBlue,
    primaryContainer = SkyBlue,
    secondary = Teal200,
    background = MediumBlue,
    onBackground = Black,
    onPrimary = White
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ExpRoadTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}