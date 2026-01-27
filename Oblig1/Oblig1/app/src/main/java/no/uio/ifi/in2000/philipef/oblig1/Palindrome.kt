package no.uio.ifi.in2000.philipef.oblig1

fun isPalindrome(text: String): Boolean {
    return text.lowercase() == text.lowercase().reversed()
}