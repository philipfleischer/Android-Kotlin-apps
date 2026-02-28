# Fragments in Android (Kotlin)

## This project demonstrates how to create and use Fragments in Android using Kotlin.

### It is based on a short tutorial and serves as a reusable reference for implementing multiple UI components inside a single Activity.

⸻

### Purpose

The goal of this project is to understand:
- What a Fragment is
- How to create Fragment classes
- How to use FragmentContainerView
- How to switch between Fragments dynamically
- How Fragments differ from Activities

Fragments allow modular and reusable UI components within an Activity.

⸻

### Project Structure

MainActivity.kt              -> Hosts and switches fragments
activity_main.xml            -> Contains FragmentContainerView + Buttons
Fragment1.kt                 -> First Fragment
Fragment2.kt                 -> Second Fragment
fragment_1.xml               -> Layout for Fragment 1
fragment_2.xml               -> Layout for Fragment 2


⸻

### How It Works

#### 1. Activity Layout

activity_main.xml contains a FragmentContainerView:
´´´
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragmentContainer"
    android:layout_width="0dp"
    android:layout_height="0dp" />
´´´
This acts as a placeholder where fragments are displayed.

Buttons are used to switch between fragments.

⸻

#### 2. Fragment Classes

Each fragment extends Fragment:

class Fragment1 : Fragment(R.layout.fragment_1)

Each fragment has its own layout file.

⸻

#### 3. Switching Fragments (MainActivity)

Fragments are replaced using FragmentManager:
´´´
supportFragmentManager.beginTransaction()
    .replace(R.id.fragmentContainer, Fragment1())
    .commit()
´´´
This dynamically swaps the content inside the container.

⸻

### How to Use This as a Template

You can reuse this structure for:
- Tab-based navigation
- Multi-section screens
- Dashboard layouts
- Modular UI components
- Larger scalable applications

To customize:
1. Add more Fragment classes.
2. Use addToBackStack() for navigation behavior.
3. Pass data between fragments.
4. Combine with ViewBinding or Navigation Component for advanced projects.

⸻

### Why Use Fragments?

Fragments allow:
- Better UI modularity
- Reusable components
- More flexible layouts (especially for tablets)
- Cleaner architecture in larger apps

They are a core concept in modern Android development.

⸻

### Summary

This project demonstrates:
- Creating Fragment classes
- Hosting them inside an Activity
- Dynamically switching fragments

It serves as a clean starting point for building multi-screen Android applications using fragments.
