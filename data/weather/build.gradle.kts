plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "ru.yasdev.weather"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.ktor.client.okhttp)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.serialization.json)

        //testImplementation("org.mockito:mockito-core:3.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
    //testImplementation("io.mockk:mockk:3.2.0")

    implementation(project(":features:weather"))
    implementation(project(":common"))
}