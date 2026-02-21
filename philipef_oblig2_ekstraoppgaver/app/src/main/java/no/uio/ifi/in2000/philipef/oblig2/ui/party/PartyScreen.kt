package no.uio.ifi.in2000.philipef.oblig2.ui.party

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import no.uio.ifi.in2000.philipef.oblig2.R
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo


@Composable
fun PartyScreen(
    partyID: String,
    navController: NavController,
    viewModel: PartyViewModel
) {
    LaunchedEffect(partyID) { viewModel.loadParty(partyID) }

    val party = viewModel.party
    if (party == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        AlpacaPartySpecific(alpaca = party, navController = navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlpacaPartySpecific(
    alpaca: PartyInfo,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val onBack: () -> Unit = { navController.popBackStack() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    TextButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Tilbake"
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Partiinfo",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp, bottom = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = alpaca.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .clip(CircleShape),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(alpaca.img)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = R.drawable.ic_broken_image),
                        placeholder = painterResource(id = R.drawable.loading_img)
                    )

                    Text(
                        text = "Leder: ${alpaca.leader}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                            .background(Color(alpaca.color.toColorInt()))
                    )

                    Text(
                        text = alpaca.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlpacaPartySpecific() {
    val sampleAlpaca = PartyInfo(
        id = "1",
        name = "AlpacaNorth",
        leader = "Chewpaca",
        img = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/assets/18788507266",
        color = "#edb879",
        description = "..."
    )
    AlpacaPartySpecific(
        alpaca = sampleAlpaca,
        navController = androidx.navigation.compose.rememberNavController()
    )
}