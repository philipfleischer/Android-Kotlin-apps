package no.uio.ifi.in2000.philipef.oblig2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import no.uio.ifi.in2000.philipef.oblig2.di.LocalDependencies
import no.uio.ifi.in2000.philipef.oblig2.ui.home.HomeScreen
import no.uio.ifi.in2000.philipef.oblig2.ui.home.HomeViewModel
import no.uio.ifi.in2000.philipef.oblig2.ui.navigation.Destinations
import no.uio.ifi.in2000.philipef.oblig2.ui.party.PartyScreen
import no.uio.ifi.in2000.philipef.oblig2.ui.party.PartyViewModel

@Composable
fun AlpacaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    alpacasViewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Home.route,
        modifier = modifier
    ) {
        composable(Destinations.Home.route) {
            HomeScreen(
                navController = navController,
                alpacaPartyUiState = alpacasViewModel.alpacaPartyUiState,
                retryAction = alpacasViewModel::getAlpacas,
                selectDistrict = alpacasViewModel::selectDistrict
            )
        }

        composable(
            route = Destinations.Party.route,
            arguments = listOf(navArgument("partyId") { type = NavType.StringType })
        ) { backStackEntry ->
            val partyId = backStackEntry.arguments?.getString("partyId").orEmpty()
            val deps = LocalDependencies.current
            val partyViewModel: PartyViewModel = viewModel(
                factory = PartyViewModel.provideFactory(deps.alpacaRepo)
            )
            PartyScreen(
                partyID = partyId,
                navController = navController,
                viewModel = partyViewModel
            )
        }
    }
}
