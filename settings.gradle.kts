rootProject.name = "CMP-WeatherApp"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google() // Remove the mavenContent { ... } block
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Remove the mavenContent { ... } block here too
        mavenCentral()
    }
}



include(":composeApp")