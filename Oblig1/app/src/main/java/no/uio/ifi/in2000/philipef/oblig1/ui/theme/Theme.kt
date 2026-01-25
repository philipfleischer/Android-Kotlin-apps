package no.uio.ifi.in2000.philipef.oblig1.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SkyPrimary,
    secondary = SkySecondary,
    tertiary = SkyTertiary
)

private val LightColorScheme = lightColorScheme(
    primary = SkyPrimary,
    secondary = SkySecondary,
    tertiary = SkyTertiary,

    background = SkyBackground,
    surface = SkySurface,
    onPrimary = SkyOnPrimary,
    onSurface = SkyOnSurface,
    outline = SkyOutline
)

@Composable
fun Philipef_oblig1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    hint: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = hint
    )
}