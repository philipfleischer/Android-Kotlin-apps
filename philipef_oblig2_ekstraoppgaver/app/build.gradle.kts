plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0"
}

android {
    namespace = "no.uio.ifi.in2000.philipef.oblig2"
    compileSdk = 35

    defaultConfig {
        applicationId = "no.uio.ifi.in2000.philipef.oblig2"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom.v20240100))
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)
    implementation(libs.androidx.compose.material3.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v262)
    implementation(libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json.v160)
    implementation(libs.okhttp)
    implementation(libs.coil.compose)
    implementation(libs.androidx.navigation.compose)
    //implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.ui.test.junit4.android)
    // implementation(libs.androidx.compose.foundation)
    // implementation(libs.androidx.compose.foundation.layout)
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.kotlinx.serialization.json.v173)
    androidTestImplementation(libs.androidx.compose.ui.ui.test.junit4)
}