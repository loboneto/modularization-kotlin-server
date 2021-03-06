val ktor_version: String by project
val kotlin_version: String by project
val ktorm_version: String by project
val my_sql_version: String by project
val koin_version: String by project
val commons_codec_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
}

group = "com.example"
version = "0.0.1"
application {

    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

tasks {
    create("stage").dependsOn("installDist")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // database
    implementation("mysql:mysql-connector-java:$my_sql_version")
    implementation("org.ktorm:ktorm-core:$ktorm_version")
    implementation("org.ktorm:ktorm-support-mysql:$ktorm_version")

    // koin
    implementation("io.insert-koin:koin-ktor:$koin_version")

    // Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")

    implementation("io.ktor:ktor-server-call-logging-jvm:$ktor_version")

    // Cors
    implementation("io.ktor:ktor-server-cors-jvm:$ktor_version")

    // Test
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}