package no.uio.ifi.in2000.philipef.oblig2.ui.party

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo

class PartyViewModel(
    private val repository: AlpacaPartiesRepository
) : ViewModel() {

    var party: PartyInfo? by mutableStateOf(null)
        private set

    fun loadParty(id: String) {
        viewModelScope.launch {
            party = repository.getPartyById(id)
        }
    }

    companion object {
        fun provideFactory(repository: AlpacaPartiesRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return PartyViewModel(repository) as T
                }
            }
    }
}
