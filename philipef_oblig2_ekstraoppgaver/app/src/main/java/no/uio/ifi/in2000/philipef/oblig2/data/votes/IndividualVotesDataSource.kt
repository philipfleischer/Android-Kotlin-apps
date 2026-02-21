package no.uio.ifi.in2000.philipef.oblig2.data.votes

import no.uio.ifi.in2000.philipef.oblig2.model.votes.District
import no.uio.ifi.in2000.philipef.oblig2.model.votes.DistrictVotes
import no.uio.ifi.in2000.philipef.oblig2.network.AlpacaApiService

class IndividualVotesDataSource(
    private val alpacaApiService: AlpacaApiService
) {
    suspend fun getVotesForDistrict(district: District): List<DistrictVotes> {
        val response = when (district) {
            District.DISTRICT_1 -> alpacaApiService.getDistrict1Votes()
            District.DISTRICT_2 -> alpacaApiService.getDistrict2Votes()
            else -> throw IllegalArgumentException("Something wrong with getVotes For District fun")
        }


        return response
            .groupingBy { it.id }
            .eachCount()
            .map { (id, count) ->
                DistrictVotes(district, id, count)
            }
    }
}