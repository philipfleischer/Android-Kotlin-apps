package no.uio.ifi.in2000.philipef.oblig2.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.AlpacaPartiesDataSource
import no.uio.ifi.in2000.philipef.oblig2.data.alpacas.DefaultAlpacasRepository
import no.uio.ifi.in2000.philipef.oblig2.data.votes.AggregatedVotesDataSource
import no.uio.ifi.in2000.philipef.oblig2.data.votes.IndividualVotesDataSource
import no.uio.ifi.in2000.philipef.oblig2.data.votes.VotesRepository
import no.uio.ifi.in2000.philipef.oblig2.network.AlpacaApiService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object AppGraph {
    fun build(): AppDependencies {
        val json = Json { ignoreUnknownKeys = true; isLenient = true }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://in2000-proxy.ifi.uio.no/alpacaapi/v2/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val api = retrofit.create(AlpacaApiService::class.java)

        val votesRepo = VotesRepository(
            IndividualVotesDataSource(api),
            AggregatedVotesDataSource(api)
        )

        //val alpacaRepo = DefaultAlpacasRepository(api, votesRepo)

        val dataSource = AlpacaPartiesDataSource(api)
        val alpacaRepo =
            DefaultAlpacasRepository(dataSource = dataSource, votesRepository = votesRepo)

        return AppDependencies(
            alpacaRepo = alpacaRepo,
            votesRepo = votesRepo
        )
    }
}