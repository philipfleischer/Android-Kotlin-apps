package no.uio.ifi.in2000.philipef.oblig2.data.alpacas

import no.uio.ifi.in2000.philipef.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.philipef.oblig2.model.votes.District

interface AlpacaPartiesRepository {
    suspend fun getAlpacas(): List<PartyInfo>
    suspend fun getPartyById(id: String): PartyInfo?
    suspend fun getVotesWithNamesForDistrict(district: District): Map<String, Int>
    suspend fun getTotalVotesPerParty(): Map<String, Int>
}

class DefaultAlpacasRepository(
    private val dataSource: AlpacaPartiesDataSource,
    private val votesRepository: VotesRepository
) : AlpacaPartiesRepository {

    private var cachedParties: List<PartyInfo>? = null
    private val cachedVotesByDistrict: MutableMap<District, Map<String, Int>> = mutableMapOf()

    override suspend fun getAlpacas(): List<PartyInfo> {
        cachedParties?.let { return it }
        return dataSource.fetchParties().also { cachedParties = it }
    }

    override suspend fun getPartyById(id: String): PartyInfo? {
        val parties = getAlpacas() // using cache here
        return parties.find { it.id == id }
    }

    override suspend fun getVotesWithNamesForDistrict(district: District): Map<String, Int> {
        cachedVotesByDistrict[district]?.let { return it }

        val votes = votesRepository.getVotesForDistrict(district)
        val result = votes.associate { v -> v.alpacaPartyId to v.numberOfVotesForParty }

        cachedVotesByDistrict[district] = result
        return result
    }

    override suspend fun getTotalVotesPerParty(): Map<String, Int> {
        val district1 = votesRepository.getVotesForDistrict(District.DISTRICT_1)
        val district2 = votesRepository.getVotesForDistrict(District.DISTRICT_2)
        val district3 = votesRepository.getVotesForDistrict(District.DISTRICT_3)

        val totalVotesAllDistricts = district1 + district2 + district3
        return totalVotesAllDistricts
            .groupBy { it.alpacaPartyId }
            .mapValues { (_, votes) -> votes.sumOf { it.numberOfVotesForParty } }
    }
}



