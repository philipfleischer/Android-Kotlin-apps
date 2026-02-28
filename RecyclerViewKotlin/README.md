# RecyclerView in Android (Kotlin)

## This project demonstrates how to build a simple RecyclerView in Android using Kotlin.

### It is based on a short tutorial and serves as a reusable reference for displaying scrollable lists with custom item layouts.

⸻

### Purpose

The goal of this project is to understand:
- What a RecyclerView is
- How to create a custom item layout
- How to use a Data Class for list items
- How to implement a custom Adapter
- How to connect everything inside MainActivity

This project replaces the older ListView approach and demonstrates the modern, recommended way to display lists in Android.

⸻

### Project Structure

MainActivity.kt        -> Initializes RecyclerView and provides data
DataClass.kt           -> Model representing one item
AdapterClass.kt        -> RecyclerView Adapter
activity_main.xml      -> Contains RecyclerView
item_layout.xml        -> Layout for a single item


⸻

### How It Works

#### 1. Data Source

Two arrays are created in MainActivity:

imageList = arrayOf(...)
titleList = arrayOf(...)

Each position in the arrays represents one item in the list.

These are combined into a list of DataClass objects.

⸻

#### 2. Data Model

DataClass.kt:

data class DataClass(
    val image: Int,
    val title: String
)

This represents a single row in the RecyclerView.

⸻

#### 3. RecyclerView Setup (MainActivity)

recyclerView = findViewById(R.id.recyclerView)
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.setHasFixedSize(true)

- LinearLayoutManager makes it scroll vertically.
- setHasFixedSize(true) improves performance when size does not change.

⸻

#### 4. Adapter

AdapterClass binds each DataClass object to item_layout.xml.

The adapter:
- Creates ViewHolders
- Binds image and title to each row
- Controls how each item is displayed

⸻

### How to Use This as a Template

You can reuse this structure for:
- Displaying menus
- Showing dynamic data
- Creating settings screens
- Portfolio demo apps
- IN2000 assignments
- Any list-based UI

#### To customize:
1. Modify DataClass to include more fields.
2. Update item_layout.xml to design your row layout.
3. Replace the static arrays with real data (e.g., from API or database).
4. Add click listeners inside the Adapter for interaction.

⸻

### Why RecyclerView Instead of ListView?

RecyclerView:
- Is more efficient
- Supports ViewHolder pattern by default
- Allows complex layouts
- Is highly customizable

It is the standard way to display scrollable lists in modern Android development.

⸻

## Summary

This is a minimal but complete example of implementing a RecyclerView using:
- Custom data model
- Custom adapter
- LinearLayoutManager
- Card-based item layout

This project can serve as a clean starting point for any list-based Android application.
