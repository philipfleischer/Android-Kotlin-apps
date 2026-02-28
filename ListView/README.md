# Simple ListView in Android (Kotlin)

## This project demonstrates how to create and use a basic ListView in Android using Kotlin.

### It is based on a short tutorial and serves as a minimal reference implementation.

⸻

### Purpose

The goal of this project is to understand:
- What a ListView is
- How to define it in XML
- How to connect it to Kotlin code
- How to display a list of items using an Adapter

This project is meant for practice and can be reused as a template for small apps or assignments.

⸻

### How It Works

#### 1. Layout (activity_main.xml)

A ListView is added inside a ConstraintLayout:

´´´
<ListView
    android:id="@+id/listView"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
´´´

The ListView fills the screen using constraints.

⸻

#### 2. Kotlin (MainActivity.kt)

In MainActivity, the ListView is connected to an ArrayAdapter:

val listView = findViewById<ListView>(R.id.listView)

val items = arrayOf("Item 1", "Item 2", "Item 3")

val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

listView.adapter = adapter

This displays the list items on the screen.

⸻

### How to Use This as a Template

Reuse this structure for:
- Displaying static lists
- Showing simple menu options
- Prototyping small features
- IN2000 exercises
- Quick practice apps

To adapt it:
1. Replace the items array with your own data.
2. Add an OnItemClickListener if you want click behavior.
3. Replace simple_list_item_1 with a custom layout for more advanced UI.

⸻

### When to Use ListView

ListView is useful for:
- Simple vertical lists
- Beginner Android practice
- Understanding adapters

⸻

This project is part of Android practice and portfolio development and can serve as a clean starting point for future small applications.
