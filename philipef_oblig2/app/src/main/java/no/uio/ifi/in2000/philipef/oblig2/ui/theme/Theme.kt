package no.uio.ifi.in2000.philipef.oblig2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = androidx.compose.ui.graphics.Color.White,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    background = androidx.compose.ui.graphics.Color.Black,
    onBackground = androidx.compose.ui.graphics.Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = androidx.compose.ui.graphics.Color.Black,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    background = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color.Black
)

@Composable
fun AlpacasTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}


val AlpacasTypography = Typography(
    titleLarge = TextStyle(
        fontSize = 44.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold
    )
)
