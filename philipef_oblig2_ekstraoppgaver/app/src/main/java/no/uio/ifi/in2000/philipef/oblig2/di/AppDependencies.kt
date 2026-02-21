package no.uio.ifi.in2000.philipef.oblig2.di

import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.philipef.oblig2.data.votes.VotesRepository

data class AppDependencies(
    val alpacaRepo: AlpacaPartiesRepository,
    val votesRepo: VotesRepository
)