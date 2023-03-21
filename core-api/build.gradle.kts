@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace ="mz.co.bilheteira.api"
    compileSdk=33
    defaultConfig { minSdk = 25 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    api(libs.retrofit)
    api(libs.retrofit.moshi.converter)
    api(libs.logging.interceptor)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.codegen)

    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

    implementation(libs.core.ktx)

    testImplementation(libs.junit)
}