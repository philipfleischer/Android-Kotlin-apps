# Spinner in Android (Kotlin)

## This project demonstrates how to create and use a Spinner (dropdown menu) in Android using Kotlin.

### It is based on a short tutorial and serves as a simple reference for implementing selectable dropdown options in an Android app.

⸻

### Purpose

The goal of this project is to understand:
- What a Spinner is
- How to define it in XML
- How to populate it using an ArrayAdapter
- How to handle item selection

This is a foundational UI component useful for forms, filters, and user input.

⸻

### How It Works

#### 1. Layout (activity_main.xml)

A Spinner is added to the layout:
'''
<Spinner
    android:id="@+id/spinner"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
'''

⸻

#### 2. Kotlin Setup (MainActivity.kt)

A list of options is created and attached using an ArrayAdapter:

val spinner = findViewById<Spinner>(R.id.spinner)

val options = arrayOf("Option 1", "Option 2", "Option 3")

val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

spinner.adapter = adapter

You can also set an OnItemSelectedListener to respond to user selection.

⸻

### How to Use This as a Template

You can reuse this structure for:
- Dropdown selections in forms
- Settings menus
- Category filters
- Simple food-ordering or selection apps
- Beginner Android practice projects

To customize:
1. Replace the static array with your own values.
2. Load options dynamically from an API or database.
3. Handle selected item inside the listener for further actions.

⸻

### Summary

This project is a minimal example of implementing a Spinner using:
- XML layout
- ArrayAdapter
- Selection handling

It serves as a clean starting point for any Android app requiring dropdown selection functionality.
