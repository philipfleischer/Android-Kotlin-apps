package no.uio.ifi.in2000.philipef.oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.philipef.oblig1.navigation.NavGraph
import no.uio.ifi.in2000.philipef.oblig1.ui.theme.Philipef_oblig1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}

@Composable
fun App() {
    Philipef_oblig1Theme {
        Surface {
            val navController = rememberNavController()
            NavGraph(navController = navController)
        }
    }
}