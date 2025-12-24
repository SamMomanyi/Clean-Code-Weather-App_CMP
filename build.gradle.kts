plugins {
    // Android Plugins
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false

    // Kotlin & Compose Plugins
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.composeHotReload) apply false

    // ✅ ADDED THIS: Serialization (Needed for JSON/API)
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false

    // ✅ KSP & Room (Needed for Database)
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.room) apply false
}