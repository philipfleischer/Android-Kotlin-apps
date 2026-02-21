package no.uio.ifi.in2000.philipef.oblig2.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import no.uio.ifi.in2000.philipef.oblig2.R
import no.uio.ifi.in2000.philipef.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.philipef.oblig2.model.votes.District
import no.uio.ifi.in2000.philipef.oblig2.ui.navigation.Destinations
import no.uio.ifi.in2000.philipef.oblig2.ui.theme.AlpacasTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    alpacaPartyUiState: AlpacaPartyUiState,
    retryAction: () -> Unit,
    selectDistrict: (District) -> Unit,
    modifier: Modifier = Modifier
) {
    when (alpacaPartyUiState) {
        is AlpacaPartyUiState.Loading -> LoadingScreen(modifier.size(200.dp))

        is AlpacaPartyUiState.Success -> {
            val partyInfoMap =
                alpacaPartyUiState.alpacas.associate { alpaca -> alpaca.id to alpaca.name }

            Scaffold(
                modifier = modifier.fillMaxSize(),
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = UiStrings.APP_TITLE,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                fontSize = 44.sp
                            )
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
                    )
                }
            ) { paddingValues ->
                HomeContent(
                    navController = navController,
                    selectedDistrict = alpacaPartyUiState.selectedDistrict,
                    onDistrictSelected = selectDistrict,
                    votes = alpacaPartyUiState.votes,
                    partyInfo = partyInfoMap,
                    alpacas = alpacaPartyUiState.alpacas,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }

        is AlpacaPartyUiState.Error -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun DistrictDropdown(
    selectedDistrict: District,
    onDistrictSelected: (District) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Distrikt: ${selectedDistrict.name.replace('_', ' ')}",
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            District.entries.forEach { district ->
                DropdownMenuItem(
                    text = { Text(district.name.replace('_', ' ')) },
                    onClick = {
                        onDistrictSelected(district)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading",
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed to load")
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun AlpacaCard(
    alpaca: PartyInfo,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onClick(alpaca.id) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = alpaca.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(context = LocalContext.current)
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
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .background(Color(android.graphics.Color.parseColor(alpaca.color)))
            )
        }
    }
}


@Composable
fun AlpacaCardGrid(
    alpacas: List<PartyInfo>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
    ) {
        items(alpacas) { alpaca ->
            AlpacaCard(
                alpaca = alpaca,
                onClick = {
                    val partyID = alpaca.id
                    navController.navigate(Destinations.Party.create(partyID))
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AlpacasTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AlpacasTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}


@Preview(showBackground = true)
@Composable
fun AlpacaCardGridPreview() {
    AlpacasTheme {
        val mockData = List(10) {
            PartyInfo(
                "5",
                "DESIR",
                "Chewbacca",
                "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/assets/18788507266",
                "#edb879",
                "Chewbacca, med."
            )
        }
        AlpacaCardGrid(
            mockData,
            navController = rememberNavController(),
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
private fun HomeContent(
    navController: NavController,
    selectedDistrict: District,
    onDistrictSelected: (District) -> Unit,
    votes: Map<String, Int>,
    partyInfo: Map<String, String>,
    alpacas: List<PartyInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        item {
            SectionHeader(
                title = UiStrings.OVERVIEW_TITLE,
                subtitle = UiStrings.OVERVIEW_SUBTITLE
            )
        }

        item {
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.outlinedCardColors()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = UiStrings.VOTES_TITLE,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(Modifier.height(10.dp))

                    DistrictDropdown(
                        selectedDistrict = selectedDistrict,
                        onDistrictSelected = onDistrictSelected
                    )

                    Spacer(Modifier.height(12.dp))

                    Divider(color = MaterialTheme.colorScheme.outlineVariant)

                    Spacer(Modifier.height(12.dp))

                    VoteList(
                        votes = votes,
                        partyInfo = partyInfo
                    )

                    VoteBarChart(votes = votes, parties = alpacas)
                }
            }
        }

        item {
            SectionHeader(
                title = UiStrings.PARTIES_TITLE,
                subtitle = "${alpacas.size} tilgjengelig"
            )
        }

        item {
            val top4 = alpacas.take(4)

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Rad 1
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    top4.getOrNull(0)?.let { alpaca ->
                        AlpacaCard(
                            alpaca = alpaca,
                            onClick = { partyId ->
                                navController.navigate(
                                    Destinations.Party.create(
                                        partyId
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } ?: Spacer(Modifier.weight(1f))

                    top4.getOrNull(1)?.let { alpaca ->
                        AlpacaCard(
                            alpaca = alpaca,
                            onClick = { partyId ->
                                navController.navigate(
                                    Destinations.Party.create(
                                        partyId
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } ?: Spacer(Modifier.weight(1f))
                }

                // Rad 2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    top4.getOrNull(2)?.let { alpaca ->
                        AlpacaCard(
                            alpaca = alpaca,
                            onClick = { partyId ->
                                navController.navigate(
                                    Destinations.Party.create(
                                        partyId
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } ?: Spacer(Modifier.weight(1f))

                    top4.getOrNull(3)?.let { alpaca ->
                        AlpacaCard(
                            alpaca = alpaca,
                            onClick = { partyId ->
                                navController.navigate(
                                    Destinations.Party.create(
                                        partyId
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f)
                        )
                    } ?: Spacer(Modifier.weight(1f))
                }
            }
        }

        item { Spacer(Modifier.height(8.dp)) }
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                fontSize = 34.sp
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        Divider(
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}