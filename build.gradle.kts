import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "ixidev.jetspring"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
            kotlinOptions.freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
        }
        withJava()
    }
    sourceSets {
        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
                implementation(compose.desktop.currentOs)
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-security")
                implementation("org.jetbrains.kotlin:kotlin-reflect")
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
                runtimeOnly("mysql:mysql-connector-java")
                implementation("br.com.devsrsouza.compose.icons.jetbrains:font-awesome:1.0.0")
                implementation("com.github.lgooddatepicker:LGoodDatePicker:11.2.1")
                compileOnly("org.projectlombok:lombok")

            }
        }
        @Suppress("UNUSED_VARIABLE") val jvmTest by getting {
            dependencies {
                compileOnly("org.projectlombok:lombok")
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("org.jetbrains.compose.ui:ui-test-junit4-desktop:1.1.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "ixidev.jetspring.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "JetSpring"
            packageVersion = "1.0.0"
            modules(
                "java.instrument",
                "java.management",
                "java.prefs",
                "java.rmi",
                "java.scripting",
                "java.sql.rowset",
                "jdk.httpserver",
                "jdk.jfr",
                "jdk.unsupported"
            )
            linux {
                this.shortcut = true
                iconFile.set(file("app-icon.svg"))
            }
        }
    }
}
