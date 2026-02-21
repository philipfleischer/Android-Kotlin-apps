package no.uio.ifi.in2000.philipef.oblig2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import no.uio.ifi.in2000.philipef.oblig2.AlpacaNavHost
import no.uio.ifi.in2000.philipef.oblig2.di.LocalDependencies
import no.uio.ifi.in2000.philipef.oblig2.ui.home.AlpacaPartyUiState
import no.uio.ifi.in2000.philipef.oblig2.ui.home.HomeViewModel
import no.uio.ifi.in2000.philipef.oblig2.ui.home.alpacasViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlpacaApp(navController: NavHostController) {
    val deps = LocalDependencies.current

    val alpacasViewModel: HomeViewModel = viewModel(
        factory = alpacasViewModelFactory(deps.alpacaRepo, deps.votesRepo)
    )

    val snackbarHostState = remember { SnackbarHostState() }

    // Showing the snackbar when error screen
    LaunchedEffect(alpacasViewModel.alpacaPartyUiState) {
        if (alpacasViewModel.alpacaPartyUiState is AlpacaPartyUiState.Error) {
            snackbarHostState.showSnackbar(
                message = "Could not render data, check internet connection and try again."
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        AlpacaNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            alpacasViewModel = alpacasViewModel
        )
    }
}
