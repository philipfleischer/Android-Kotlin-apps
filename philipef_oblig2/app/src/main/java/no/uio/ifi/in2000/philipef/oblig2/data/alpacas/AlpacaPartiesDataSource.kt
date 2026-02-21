package no.uio.ifi.in2000.philipef.oblig2.data.alpacas

import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.philipef.oblig2.network.AlpacaApiService

class AlpacaPartiesDataSource(
    private val api: AlpacaApiService
) {
    suspend fun fetchParties(): List<PartyInfo> {
        return api.getAlpacas().parties
    }
}