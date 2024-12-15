version = "0.0.1-a.1"

plugins {
    application
}

base {
    archivesName = "statesrestorer"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_5
    targetCompatibility = JavaVersion.VERSION_1_5
    withSourcesJar()
    withJavadocJar()
}
