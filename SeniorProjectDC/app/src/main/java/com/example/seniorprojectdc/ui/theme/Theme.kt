package com.example.seniorprojectdc.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val LightColorScheme = lightColorScheme(
    primary = Green500,
    onPrimary = WhiteText,
    secondary = LightGreen,
    onSecondary = BlackText,
    background = Green50,
    onBackground = BlackText,
    surface = Green100,
    onSurface = BlackText,
)

val DarkColorScheme = darkColorScheme(
    primary = Green300,
    onPrimary = BlackText,
    secondary = DarkGreen,
    onSecondary = BlackText,
    background = Green900,
    onBackground = WhiteText,
    surface = Green800,
    onSurface = WhiteText,
)

@Composable
fun SeniorProjectDCTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}