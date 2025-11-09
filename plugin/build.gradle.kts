plugins {
    `kotlin-dsl`
}

group = "com.elliancarlos.gasoline"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
}

gradlePlugin {
    plugins.create("gasoline") {
        id = "com.elliancarlos.gasoline"
        implementationClass = "com.elliancarlos.gasoline.GasolinePlugin"
    }
}