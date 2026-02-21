package no.uio.ifi.in2000.philipef.oblig2.ui.navigation

sealed class Destinations(val route: String) {
    data object Home : Destinations("home")
    data object Party : Destinations("party/{partyId}") {
        fun create(partyId: String) = "party/$partyId"
    }
}