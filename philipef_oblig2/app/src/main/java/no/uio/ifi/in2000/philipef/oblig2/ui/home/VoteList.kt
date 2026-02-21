package no.uio.ifi.in2000.philipef.oblig2.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VoteList(votes: Map<String, Int>, partyInfo: Map<String, String>) {
    val sortedVotes =
        votes.entries.sortedWith(compareBy({ it.key.toIntOrNull() ?: Int.MAX_VALUE }, { it.key }))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Parti",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "Antall stemmer",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Divider()
        sortedVotes.forEachIndexed { index, (partyId, voteCount) ->
            val partyName = partyInfo[partyId] ?: "Feil parti funnet her"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(partyName, style = MaterialTheme.typography.bodyMedium)
                Text(voteCount.toString(), style = MaterialTheme.typography.bodyMedium)
            }
            if (index != sortedVotes.lastIndex)
                Divider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoteListPreview() {
    val sampleVotes = mapOf(
        "1" to 120,
        "2" to 75,
        "3" to 200,
        "4" to 95999
    )
    val samplePartyInfo = mapOf(
        "1" to "Nord",
        "2" to "Sor",
        "3" to "Ost",
        "4" to "Vest"
    )
    VoteList(
        votes = sampleVotes,
        partyInfo = samplePartyInfo
    )
}