import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    repositories {
        System.getenv()["NIX_MAVEN_REPO"]?.let {
            mavenLocal {
                url = uri(it)
                metadataSources {
                    mavenPom()
                    gradleMetadata()
                }
            }
        } ?: run { mavenCentral() }
    }
}

plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.example"

version = "1.0-SNAPSHOT"

dependencies { }

tasks.test { useJUnitPlatform() }

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "21" }

application { mainClass.set("MainKt") }
