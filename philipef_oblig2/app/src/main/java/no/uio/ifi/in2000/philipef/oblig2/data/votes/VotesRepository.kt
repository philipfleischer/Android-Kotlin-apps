package no.uio.ifi.in2000.philipef.oblig2.data.votes

import no.uio.ifi.in2000.philipef.oblig2.model.votes.District
import no.uio.ifi.in2000.philipef.oblig2.model.votes.DistrictVotes

class VotesRepository(
    private val individualVotesDataSource: IndividualVotesDataSource,
    private val aggregatedVotesDataSource: AggregatedVotesDataSource
) {
    suspend fun getVotesForDistrict(district: District): List<DistrictVotes> =
        when (district) {
            District.DISTRICT_1, District.DISTRICT_2 -> individualVotesDataSource.getVotesForDistrict(
                district
            )

            District.DISTRICT_3 -> aggregatedVotesDataSource.getVotesForDistrict(district)
        }

}
