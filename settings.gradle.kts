pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("jvm") version "1.9.23"
    }
    includeBuild("plugin")
}

rootProject.name = "gasoline-root"

include("tester")