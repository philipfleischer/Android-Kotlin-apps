package no.uio.ifi.in2000.philipef.oblig1.ui.unitconverter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.philipef.oblig1.converter
import no.uio.ifi.in2000.philipef.oblig1.data.UnitOptions
import no.uio.ifi.in2000.philipef.oblig1.domain.ConverterUnits
import no.uio.ifi.in2000.philipef.oblig1.navigation.ScreenNavButton
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.ContentCard
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Philipef_oblig1Theme
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Spacing
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.skyGradient

@Composable
fun UnitConverterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Spacing.large)
            .background(skyGradient)
    ) {
        ContentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Spacing.medium)
        ) {
            UnitConverter(
                modifier = Modifier
                    .padding(top = Spacing.medium)
                    .fillMaxWidth()
            )
        }

        ScreenNavButton(
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var amountText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var result by remember { mutableIntStateOf(0) }
    var selectedUnit by remember { mutableStateOf(UnitOptions("Ounce", ConverterUnits.OUNCE)) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val amountLong = amountText.toLongOrNull() ?: 0L
    val amount = amountLong.coerceIn(0L, Int.MAX_VALUE.toLong()).toInt()
    //val amount = amountText.toIntOrNull() ?: 0

    LaunchedEffect(amount, selectedUnit.unit) {
        result = converter(amount, selectedUnit.unit)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
        )
    ) {
        Column(
            modifier = Modifier.padding(Spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Text(
                text = "Unit converter",
                style = MaterialTheme.typography.headlineLarge
            )

            HorizontalDivider(
                modifier = Modifier
                    .width(230.dp)
                    .padding(top = 4.dp),
                thickness = 3.dp,
                color = MaterialTheme.colorScheme.primary
            )

            UnitDropDown(
                selectedUnit = selectedUnit,
                unitOptions = ConverterUnits.entries.map { UnitOptions(it.name, it) },
                onUnitSelected = { selectedUnit = it },
                expanded = expanded,
                onExpandedChange = { expanded = it }
            )


            OutlinedTextField(
                value = amountText,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) {
                        amountText = newValue
                    }
                },
                label = { Text("Amount (${selectedUnit.name})") },
                placeholder = { Text("e.g.: 12") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                )
            )

            ConversionResultField(amount, selectedUnit.name, result)
        }
    }
}


@Composable
fun UnitDropDown(
    selectedUnit: UnitOptions,
    unitOptions: List<UnitOptions>,
    expanded: Boolean,
    onUnitSelected: (UnitOptions) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { onExpandedChange(true) },
            modifier = Modifier.fillMaxWidth(),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(selectedUnit.name)
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            unitOptions.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(unit.name) },
                    onClick = {
                        onUnitSelected(unit)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}


@Composable
fun ConversionResultField(amount: Int, unit: String, result: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f))
            .padding(Spacing.medium),
    ) {
        Text(
            text = "$amount ${unit}s = $result L",
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun UnitConverterScreenPreview() {
    Philipef_oblig1Theme {
        UnitConverterScreen(navController = rememberNavController())
    }
}















