import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jetbrains.kotlin.kapt") version kotlinVersion
    id("org.sonarqube") version "3.3"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://artifactory.euw.platformservices.io/artifactory/sbg-tds-release/")
    mavenCentral()
}

dependencies {
    // Kotlin - versions not specified defined by kotlin plugin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.4")

    val ktorVersion = "2.3.1"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-apache5:$ktorVersion")
    val apache5Version = "5.2.1"
    implementation("org.apache.httpcomponents.client5:httpclient5:$apache5Version")
    implementation("org.apache.httpcomponents.core5:httpcore5:$apache5Version")
    implementation("org.apache.httpcomponents.core5:httpcore5-h2:$apache5Version")

    // Spring boot
    implementation(platform("org.springframework.boot:spring-boot-dependencies:2.6.7"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // TDS
    val tdCommonCoreVersion = "1.0.7"
    val tdCommonDomainVersion = "1.9.0"
    implementation("com.skybet.trading.tds:td-common-core:$tdCommonCoreVersion")
    implementation("com.skybet.trading.tds:td-common-domain:$tdCommonDomainVersion") {
        exclude(group = "com.skybet.trading", module = "market-core")
    }

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}