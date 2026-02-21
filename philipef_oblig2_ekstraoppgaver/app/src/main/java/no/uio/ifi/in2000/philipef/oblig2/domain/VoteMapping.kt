package no.uio.ifi.in2000.philipef.oblig2.domain

import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.philipef.oblig2.model.votes.DistrictVotes

fun List<DistrictVotes>.toNameVoteMap(parties: List<PartyInfo>): Map<String, Int> {
    val idToName = parties.associate { it.id to it.name }
    return associate { vote ->
        (idToName[vote.alpacaPartyId] ?: "Ukjent") to vote.numberOfVotesForParty
    }
}