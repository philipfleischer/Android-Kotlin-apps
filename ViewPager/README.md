# ViewPager2 in Android (Kotlin)

## This project demonstrates how to create and use ViewPager2 in Android using Kotlin.

### It is based on a short tutorial and serves as a reference for implementing swipeable screens inside an Android app.

⸻

### Purpose

The goal of this project is to understand:
- What ViewPager2 is
- How to create swipeable pages
- How to use a custom adapter
- How to connect layouts with ViewPager2

ViewPager2 allows horizontal swiping between multiple screens.

⸻

### Project Structure

MainActivity.kt        → Initializes ViewPager2
activity_main.xml      → Contains ViewPager2
page_layout.xml        → Layout for a single page
ViewPagerAdapter.kt    → Adapter for supplying pages


⸻

### How It Works

#### 1. Layout (activity_main.xml)

Add ViewPager2 to the Activity:
´´´
<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
´´´

⸻

#### 2. Page Layout (page_layout.xml)

Defines how each individual page looks (e.g., TextView, ImageView, CardView).

⸻

#### 3. Adapter

A RecyclerView-style adapter provides page content:
´´´
class ViewPagerAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
´´´
The adapter binds data to each page layout.

⸻

#### 4. MainActivity Setup
´´´
val viewPager = findViewById<ViewPager2>(R.id.viewPager)

val items = listOf("Page 1", "Page 2", "Page 3")

viewPager.adapter = ViewPagerAdapter(items)
´´´
This connects the data to the ViewPager2.

⸻

### How to Use This as a Template

You can reuse this structure for:
- Onboarding screens
- Image sliders
- Tutorial pages
- Swipe-based navigation
- TabLayout integration

To customize:
1. Replace static data with dynamic content.
2. Use Fragments instead of simple layouts.
3. Combine with TabLayout for tab navigation.
4. Add animations or transformations.

⸻

### Why ViewPager2?

ViewPager2:
- Supports vertical and horizontal swiping
- Uses RecyclerView internally (better performance)
- Supports FragmentStateAdapter
- Is the modern replacement for ViewPager

⸻

### Summary

This project demonstrates:
- Setting up ViewPager2
- Creating a custom adapter
- Designing swipeable page layouts

It serves as a clean template for implementing swipe navigation in Android applications.
