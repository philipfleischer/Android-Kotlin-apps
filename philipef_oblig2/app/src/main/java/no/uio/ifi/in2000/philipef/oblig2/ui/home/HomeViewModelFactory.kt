package no.uio.ifi.in2000.philipef.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.philipef.oblig2.data.votes.VotesRepository

fun alpacasViewModelFactory(
    alpacaRepo: AlpacaPartiesRepository,
    votesRepo: VotesRepository
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(alpacaRepo, votesRepo) as T
    }
}