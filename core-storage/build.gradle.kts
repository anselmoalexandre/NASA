@Suppress("DSL_SCOPE_VIOLATION") // Refer to issue #22797(https://github.com/gradle/gradle/issues/22797) for more info.
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "mz.co.bilheteira.storage"
    compileSdk = 33
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

    implementation(libs.core.ktx)

    implementation(libs.room)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    implementation(libs.moshi.codegen)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.truth)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
}