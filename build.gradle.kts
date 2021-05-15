import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Import values from gradle.properties
val kotlinVersion: String by project

val koinVersion: String by project
val kediatrVersion: String by project
val valiktorVersion: String by project
val cliktVersion: String by project
val serializationVersion: String by project
val ktorVersion: String by project

val kotestVersion: String by project
val kotestKoinVersion: String by project
// END: Import values from gradle.properties

plugins {
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.serialization") version "1.4.32"

    id("com.github.roroche.plantuml") version "1.0.2"

    application
}

group = "it.oechsler"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Dependencies used for building and running the application
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("com.trendyol:kediatr-core:$kediatrVersion")
    implementation("org.valiktor:valiktor-core:$valiktorVersion")
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-dependencies:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    // END: Dependencies used for building and running the application

    // Dependencies used for testing the application
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-koin:$kotestKoinVersion")
    // END: Dependencies used for testing the application
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("it.oechsler.MainKt")
}

sourceSets.main {
    java.srcDir("src")
    resources.srcDir("resources")
}

sourceSets.test {
    java.srcDir("test")
    resources.srcDir("testresources")
}

classDiagram {
    val outputFolder = file("$buildDir/diagrams/")
    outputFolder.mkdirs()

    packageName = "it.oechsler"
    outputFile = project.file("$outputFolder/class-diagram.plantuml")
}

