plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    kotlin("android")
}

android {
    namespace = "com.samuelrmos.mobiledemo.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.samuelrmos.mobiledemo.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.compose.tooling)
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.activity.compose)
    implementation(libs.kotlinx)
}