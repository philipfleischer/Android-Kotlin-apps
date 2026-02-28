# Splash Screen Setup Documentation

## This document explains exactly how the custom Splash Screen is configured in this project. It is written as a reproducible guide so the same setup can be recreated later without debugging.

⸻

### Objective

Implement a custom Android Splash Screen with:
- Custom logo
- Custom background color
- Fixed display duration (3 seconds)
- Automatic transition to the main app theme

⸻

### Files Involved

The following files are directly related to the splash screen configuration:

app/src/main/AndroidManifest.xml
app/src/main/java/.../MainActivity.kt
app/src/main/res/values/themes.xml
app/src/main/res/drawable/logopef.png
app/build.gradle (Module: app)

If the splash screen stops working, these are the files to inspect.

⸻

### Step 1 – Add the Logo Drawable

Location:

app/src/main/res/drawable/logopef.png

Requirements_
- File must be placed in res/drawable/
- File name must be lowercase
- No spaces
- No hyphens
- Do not place it in mipmap

Correct reference format:

@drawable/logopef


⸻

### Step 2 – Configure Themes

File:

app/src/main/res/values/themes.xml

Two themes are required:
1. The normal application theme
2. The splash theme

⸻

### 2.1 Normal Application Theme

This is the theme used after the splash screen disappears.

```
<style name="Theme.SplashScreenPEF" parent="Theme.Material3.DayNight.NoActionBar">
    <item name="colorPrimary">@color/yellow</item>
    <item name="android:statusBarColor">@color/yellow</item>
</style>
```
This must match the app’s main theme.

⸻

2.2 Splash Theme

```
<style name="Theme.App.SplashScreen" parent="Theme.SplashScreen">
    <item name="windowSplashScreenBackground">@color/yellow</item>
    <item name="windowSplashScreenAnimatedIcon">@drawable/logopef</item>
    <item name="postSplashScreenTheme">@style/Theme.SplashScreenPEF</item>
</style>
````

Important:
- The parent must be Theme.SplashScreen
- windowSplashScreenAnimatedIcon must reference the drawable
- postSplashScreenTheme must point to the normal app theme

If the parent is incorrect, Android will display the default splash screen icon.

⸻

### Step 3 – AndroidManifest Configuration

File:

AndroidManifest.xml

The activity must use the splash theme.

Correct setup:

```
<application
    android:theme="@style/Theme.SplashScreenPEF"
    ... >

    <activity
        android:name=".MainActivity"
        android:exported="true"
        android:theme="@style/Theme.App.SplashScreen">

        <intent-filter>
            <action android:name="android.intent.action.MAIN"/>
            <category android:name="android.intent.category.LAUNCHER"/>
        </intent-filter>

    </activity>

</application>
```


⸻

### Step 4 – MainActivity Implementation

File:

MainActivity.kt

Use ComponentActivity (not AppCompatActivity) to avoid theme conflicts.

```
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)

        val start = System.currentTimeMillis()
        splash.setKeepOnScreenCondition {
            System.currentTimeMillis() - start < 3000
        }

        setContentView(R.layout.activity_main)
    }
}
````

Key points:
- installSplashScreen() must be called before super.onCreate()
- setKeepOnScreenCondition controls duration
- No Thread.sleep() should be used

⸻

### Step 5 – Add SplashScreen Dependency

File:

app/build.gradle

Dependency:

implementation("androidx.core:core-splashscreen:1.0.0")

After adding:
- Sync Gradle
- Clean project
- Rebuild

⸻

If Splash Screen Shows Default Icon

Check the following in order:
1. Activity theme is set to Theme.App.SplashScreen
2. Drawable file exists in res/drawable
3. Drawable file name is valid
4. Parent theme is Theme.SplashScreen
5. Clean and rebuild project
6. Uninstall app and reinstall

⸻

Clean Rebuild Procedure

If changes are not reflected:
1. Build -> Clean Project
2. Build -> Rebuild Project
3. Uninstall the app from emulator/device
4. Run again

⸻

Summary

Splash Screen requires:
- Proper drawable placement
- Correct theme inheritance
- Activity-level splash theme
- Proper post-splash theme
- Correct dependency
- Clean build

If all these are configured exactly as documented above, the custom splash screen will function correctly.
