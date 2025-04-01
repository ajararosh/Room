plugins {


    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

//    id("com.google.devtools.ksp") // ksp
    kotlin("kapt")

}

android {
    namespace = "com.example.contactsmanagerapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.contactsmanagerapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17" // change hava 11 to 17
    }
    buildFeatures{
        dataBinding = true
    }
}

dependencies {
    kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room Database
    // Room Database uses annotations
    // Java base annotation processing tool
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
//    annotationProcessor("androidx.room:room-compiler:$room_version")

    // Annotation tool introduced by jetbrains faster than kapt
    // use ksp or kapt but ksp is not compatible with data binding
    kapt("androidx.room:room-compiler:$room_version")


    // Kotlin Extensions and Coroutines support for Room
    // ROOM with coroutines
    implementation("androidx.room:room-ktx:$room_version")

    // Coroutines: help to manage long running task
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    var lifecycle_version = "2.7.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")



}