package no.uio.ifi.in2000.philipef.oblig1.ui.palindrome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.philipef.oblig1.navigation.ScreenNavButton
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.ContentCard
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Philipef_oblig1Theme
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Spacing
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.skyGradient


@Composable
fun PalindromeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Spacing.large)
            .background(skyGradient)
    ) {
        ContentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.medium)
        ) {
            PalindromeChecker(modifier = Modifier.padding(top = Spacing.medium))
        }

        ScreenNavButton(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PalindromeScreenPreview() {
    Philipef_oblig1Theme {
        PalindromeScreen(navController = rememberNavController())
    }
}