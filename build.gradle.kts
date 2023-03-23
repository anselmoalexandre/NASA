plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.safe.args) apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
}