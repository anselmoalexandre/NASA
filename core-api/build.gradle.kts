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

    defaultConfig {
        minSdk =25
        targetSdk =33

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
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

    api(libs.retrofit)
    api(libs.retrofit.moshi.converter)
    api(libs.logging.interceptor)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.codegen)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.core.ktx)

    testImplementation(libs.junit)
}