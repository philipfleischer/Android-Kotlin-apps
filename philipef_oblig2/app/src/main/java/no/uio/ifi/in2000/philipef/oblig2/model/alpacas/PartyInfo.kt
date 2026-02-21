package no.uio.ifi.in2000.philipef.oblig2.model.alpacas

import kotlinx.serialization.Serializable

@Serializable
data class AlpacaPartiesResponse(
    val parties: List<PartyInfo>
)

@Serializable
data class PartyInfo(
    val id: String,
    val name: String = "",
    val leader: String = "",
    val img: String = "",
    val color: String = "",
    val description: String = ""
)





