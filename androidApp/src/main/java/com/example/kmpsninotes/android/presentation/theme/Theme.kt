package com.example.kmpsninotes.android.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = Colors(
    primary = Color(0xFF6750A4),
    primaryTitleColor = Color(0xFF1D1B20),
    primaryBackground =  Color(0xFFF6EDFF),
    primarySubtitleColor = Color(0xFF49454F),
    secondPrimaryTitleColor = Color.White,
    secondPrimaryBackground = Color(0xFFECE6F0),
    offline_color = Color(0xFF8C1D18),
    online_color = Color(0xFF39CCA0),
    error_color = Color(0xFFB3261E)
)

private val LightColorPalette = Colors(
    primary = Color(0xFF6750A4),
    primaryTitleColor = Color(0xFF1D1B20),
    primaryBackground =  Color(0xFFF6EDFF),
    primarySubtitleColor = Color(0xFF49454F),
    secondPrimaryTitleColor = Color.White,
    secondPrimaryBackground = Color(0xFFECE6F0),
    offline_color = Color(0xFF8C1D18),
    online_color = Color(0xFF39CCA0),
    error_color = Color(0xFFB3261E)
)

data class Colors(
    val primaryBackground:Color,
    val secondPrimaryBackground:Color,
    val primaryTitleColor:Color,
    val primarySubtitleColor:Color,
    val primary:Color,
    val secondPrimaryTitleColor:Color,
    val offline_color:Color,
    val online_color:Color,
    val error_color:Color
)

@Composable
fun SniNotesAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
   CompositionLocalProvider(
       LocalColorProvider provides LightColorPalette
   ) {
       content()
   }

}

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current
}


val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("fddfdd")
}