import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
}

kotlin {
    jvm("desktop"){
        withJava()
    }
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("org.jetbrains.kotlin:kotlin-reflect")
//            implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-security")
            implementation("mysql:mysql-connector-java:8.0.33")
//            runtimeOnly("mysql:mysql-connector-java")
            implementation("br.com.devsrsouza.compose.icons.jetbrains:font-awesome:1.0.0")
            implementation("com.github.lgooddatepicker:LGoodDatePicker:11.2.1")
            compileOnly("org.projectlombok:lombok")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}


compose.desktop {
    application {
        mainClass = "ixidev.jetspring.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ixidev.jetspring.jtspring"
            packageVersion = "1.0.0"
        }
    }
}
