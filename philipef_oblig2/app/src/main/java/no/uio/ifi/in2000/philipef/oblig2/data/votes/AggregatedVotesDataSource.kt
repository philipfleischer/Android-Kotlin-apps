package no.uio.ifi.in2000.philipef.oblig2.data.votes

import no.uio.ifi.in2000.philipef.oblig2.model.votes.District
import no.uio.ifi.in2000.philipef.oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.philipef.oblig2.network.AlpacaApiService


class AggregatedVotesDataSource(
    private val alpacaApiService: AlpacaApiService
) {
    suspend fun getVotesForDistrict(district: District): List<DistrictVotes> {
        if (district != District.DISTRICT_3) throw IllegalArgumentException("Needs to be District3")

        val response = alpacaApiService.getDistrict3Votes().parties

        return response.map { party ->
            DistrictVotes(
                district = district,
                alpacaPartyId = party.partyId,
                numberOfVotesForParty = party.votes
            )
        }
    }
}