package no.uio.ifi.in2000.philipef.oblig2.ui.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo

@Composable
fun VoteBarChart(
    votes: Map<String, Int>,
    parties: List<PartyInfo>,
    modifier: Modifier = Modifier
) {
    val entries = votes.entries.sortedByDescending { it.value }.take(8)
    val max = (entries.maxOfOrNull { it.value } ?: 1).toFloat()
    val idToParty = parties.associateBy { it.id }

    val fallbackColor = MaterialTheme.colorScheme.primary

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Parti poll (topp ${entries.size} partier)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(8.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            if (entries.isEmpty()) return@Canvas

            val barWidth = size.width / (entries.size * 1.4f)
            val gap = barWidth * 0.4f

            entries.forEachIndexed { i, e ->
                val x = i * (barWidth + gap)
                val h = (e.value / max) * size.height

                val party = idToParty[e.key]

                val barColor = try {
                    party?.color?.let { Color(android.graphics.Color.parseColor(it)) }
                        ?: fallbackColor
                } catch (_: Exception) {
                    fallbackColor
                }

                drawRect(
                    color = barColor,
                    topLeft = Offset(x, size.height - h),
                    size = Size(barWidth, h)
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        entries.forEach { (id, count) ->
            val name = idToParty[id]?.name ?: id
            Text(text = "$name: $count", style = MaterialTheme.typography.bodySmall)
        }
    }
}