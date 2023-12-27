plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.realm.kotlin")
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}

android {
    namespace = "com.example.rickmasterstestquest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.rickmasterstestquest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.ktx)
    implementation(libs.lifecycle)
    implementation(libs.viewmodel)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.converter)
    implementation(libs.coroutines)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    kapt(libs.hilt.android.compiler)
    implementation(project.dependencies.platform(libs.ktor.bom))
    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    implementation(libs.ktor.content)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.resources)
    implementation(libs.ktor.logging)
    implementation(libs.realm.base)
    implementation(libs.realm.sync)
    implementation(libs.swipe.to.reveal)
    implementation(libs.coil)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    implementation(libs.ksp)
    kapt(libs.hilt.compiler)
    debugImplementation(libs.compose.tooling.preview)
    debugImplementation(libs.compose.test.manifest)
}