# Intents in Android (Kotlin)

## This project demonstrates the basics of Intents in Android using Kotlin.

It was built while following a short tutorial and focuses on understanding:
- What an Intent is
- Explicit Intents
- Implicit Intents
- Basic Activity navigation

⸻

### What is an Intent?

An Intent is a messaging object used to request an action from another component.

Common uses:
- Start another Activity
- Pass data between Activities
- Trigger system actions (e.g., open browser)

⸻

### Explicit Intent

Used to start a specific Activity inside your app.

Example:

val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)

This directly launches SecondActivity.

⸻

### Implicit Intent

Used to request an action without specifying the exact component.

Example:

val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://android.com")
startActivity(intent)

Android decides which app can handle the request (e.g., browser).

⸻

### Project Structure

MainActivity.kt      -> Starts SecondActivity
SecondActivity.kt    -> Target screen
activity_main.xml
activity_second.xml

MainActivity contains a button that launches SecondActivity using an Explicit Intent.

⸻

### Purpose

This project is part of Android practice and portfolio development.
It serves as a simple reference for how navigation between Activities works using Intents.

⸻

This is a foundational Android concept and will be used in more advanced applications later.
