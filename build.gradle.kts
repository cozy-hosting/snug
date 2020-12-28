import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by project

val koinVersion: String by project
val kediatrVersion: String by project
val valiktorVersion: String by project
val cliktVersion: String by project
val serializationVersion: String by project
val ktorVersion: String by project

val junitVersion: String by project

plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
    application
}

group = "it.oechsler"
version = "0.0.1"


repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // Dependencies used for building and running the application
    implementation("org.koin:koin-core:$koinVersion")
    implementation("com.trendyol:kediatr-core:$kediatrVersion")
    implementation("org.valiktor:valiktor-core:$valiktorVersion")
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    // Dependencies used for testing the application
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "it.oechsler.MainKt"
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
