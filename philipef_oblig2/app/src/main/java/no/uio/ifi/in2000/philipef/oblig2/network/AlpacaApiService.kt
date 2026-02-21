package no.uio.ifi.in2000.philipef.oblig2.network

import kotlinx.serialization.Serializable
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.AlpacaPartiesResponse
import no.uio.ifi.in2000.philipef.oblig2.model.votes.IndividualVote
import retrofit2.http.GET


interface AlpacaApiService {
    @GET("alpacaparties")
    suspend fun getAlpacas(): AlpacaPartiesResponse

//    @GET("district1")
//    suspend fun getDistrict1Votes(): List<AlpacaPartyInfo>

    @GET("district1")
    suspend fun getDistrict1Votes(): List<IndividualVote>

//    @GET("district2")
//    suspend fun getDistrict2Votes(): List<AlpacaPartyInfo>

    @GET("district2")
    suspend fun getDistrict2Votes(): List<IndividualVote>

    @GET("district3")
    suspend fun getDistrict3Votes(): District3VotesResponse
}

@Serializable
data class District3VotesResponse(
    val parties: List<PartyVotes>
)

@Serializable
data class PartyVotes(
    val partyId: String,
    val votes: Int
)
