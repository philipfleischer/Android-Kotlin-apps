package no.uio.ifi.in2000.philipef.oblig1.ui.palindrome

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import no.uio.ifi.in2000.philipef.oblig1.isPalindrome
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Spacing

@Composable
fun PalindromeChecker(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    fun enterButton() {
        result = if (isPalindrome(text)) {
            "$text = Palindrome"
        } else {
            "$text = Not Palindrome"
        }
        keyboardController?.hide()
    }

    Column(
        modifier = modifier
            .padding(Spacing.medium)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Palindrome Checker",
            style = MaterialTheme.typography.headlineLarge,
        )

        HorizontalDivider(
            modifier = Modifier
                .width(230.dp)
                .padding(top = 10.dp, bottom = 30.dp),
            thickness = 3.dp,
            color = MaterialTheme.colorScheme.primary
        )

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Palindrome?") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier,
            keyboardActions = KeyboardActions(onDone = { enterButton() })
        )

        Button(
            onClick = { enterButton() },
            modifier = Modifier.padding(Spacing.medium)
        ) {
            Text(text = "Check")
        }

        if (result.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .border(2.dp, Color.Black)
                    .padding(Spacing.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}













