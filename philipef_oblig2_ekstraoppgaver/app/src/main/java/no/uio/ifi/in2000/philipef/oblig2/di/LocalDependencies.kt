package no.uio.ifi.in2000.philipef.oblig2.di

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDependencies = staticCompositionLocalOf<AppDependencies> {
    error("AppDependencies not provided")
}