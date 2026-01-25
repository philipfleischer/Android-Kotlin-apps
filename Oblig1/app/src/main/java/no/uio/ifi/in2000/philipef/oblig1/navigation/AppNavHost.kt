package no.uio.ifi.in2000.philipef.oblig1.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import no.uio.ifi.in2000.philipef.oblig1.ui.palindrome.PalindromeScreen
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Routes
import no.uio.ifi.in2000.philipef.oblig1.ui.unitconverter.UnitConverterScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = Routes.PALINDROME,
    ) {
        composable(Routes.PALINDROME) {
            PalindromeScreen(navController)
        }
        composable(Routes.UNIT_CONVERTER) {
            UnitConverterScreen(navController)
        }
    }
}

@Composable
fun ScreenNavButton(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val (targetRoute, buttonText) = when (currentRoute) {
        Routes.PALINDROME -> Routes.UNIT_CONVERTER to "Go to Unit Converter"
        Routes.UNIT_CONVERTER -> Routes.PALINDROME to "Go to Palindrome"
        else -> Routes.PALINDROME to "Go to Palindrome"
    }

    Button(
        onClick = {
            navController.navigate(targetRoute) {
                launchSingleTop = true
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(1.dp),
        contentPadding = PaddingValues(horizontal = 18.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
    ) {
        Text(buttonText, fontSize = 35.sp)
    }
}