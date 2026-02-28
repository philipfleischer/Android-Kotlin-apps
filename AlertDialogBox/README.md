# Alert Dialog in Android (Kotlin)

## This project demonstrates how to create and use an AlertDialog in Android using Kotlin.

### It is based on a short tutorial and serves as a reusable reference for showing confirmation or information dialogs in Android apps.

⸻

### Purpose

The goal of this project is to understand:
- What an AlertDialog is
- How to create it using AlertDialog.Builder
- How to add buttons (Positive / Negative / Neutral)
- How to handle button actions

AlertDialogs are commonly used for confirmations, warnings, and user decisions.

⸻

### Basic Implementation

#### 1. Trigger (Button in XML)

Add a button in activity_main.xml to show the dialog:
´´´
<Button
    android:id="@+id/showDialogBtn"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Show Dialog" />
´´´

⸻

### 2. Create Dialog (MainActivity.kt)

val button = findViewById<Button>(R.id.showDialogBtn)
´´´
button.setOnClickListener {
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Confirmation")
    builder.setMessage("Are you sure you want to continue?")

    builder.setPositiveButton("Yes") { _, _ ->
        // Handle Yes
    }

    builder.setNegativeButton("No") { _, _ ->
        // Handle No
    }

    builder.setNeutralButton("Cancel") { _, _ ->
        // Handle Cancel
    }

    builder.create().show()
}
´´´

⸻

### How to Use This as a Template

Reuse this structure for:
- Exit confirmations
- Delete confirmations
- Permission explanations
- Warning messages
- Form validation alerts

To customize:
1. Change title and message.
2. Modify button labels.
3. Add logic inside button listeners.
4. Replace with a custom layout for advanced dialogs.

⸻

### Summary

This is a minimal example of implementing an AlertDialog using:
- AlertDialog.Builder
- Button click listeners
- Context from an Activity

It serves as a simple template for adding dialog-based user interactions in future Android projects.
