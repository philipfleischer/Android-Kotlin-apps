package no.uio.ifi.in2000.philipef.oblig2.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.philipef.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.philipef.oblig2.model.votes.District
import java.io.IOException

sealed interface AlpacaPartyUiState {
    data class Success(
        val alpacas: List<PartyInfo>,
        val selectedDistrict: District = District.DISTRICT_1,
        val votes: Map<String, Int> = emptyMap(),
        val totalVotes: Map<String, Int> = emptyMap()
    ) : AlpacaPartyUiState

    data object Error : AlpacaPartyUiState
    data object Loading : AlpacaPartyUiState
}

class HomeViewModel(
    private val alpacaPartiesRepository: AlpacaPartiesRepository,
    private val votesRepository: VotesRepository
) : ViewModel() {

    var alpacaPartyUiState: AlpacaPartyUiState by mutableStateOf(AlpacaPartyUiState.Loading)
        private set

    init {
        getAlpacas()
    }

    fun getAlpacas() {
        viewModelScope.launch {
            alpacaPartyUiState = AlpacaPartyUiState.Loading
            alpacaPartyUiState = try {
                val parties = alpacaPartiesRepository.getAlpacas()
                val totals = alpacaPartiesRepository.getTotalVotesPerParty()

                AlpacaPartyUiState.Success(alpacas = parties, totalVotes = totals).also {
                    selectDistrict(it.selectedDistrict)
                }
            } catch (e: IOException) {
                AlpacaPartyUiState.Error // internet exception handling
            } catch (e: Exception) {
                AlpacaPartyUiState.Error // Other exceptions
            }
        }
    }

    fun selectDistrict(district: District) {
        viewModelScope.launch {
            val votes = alpacaPartiesRepository.getVotesWithNamesForDistrict(district)

            val current = alpacaPartyUiState
            if (current is AlpacaPartyUiState.Success) {
                alpacaPartyUiState = current.copy(
                    selectedDistrict = district,
                    votes = votes
                )
            }
        }
    }
}

