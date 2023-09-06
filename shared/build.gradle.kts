plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id ("org.jetbrains.kotlin.plugin.serialization")
    id ("kotlinx-serialization")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        all{
            languageSettings.optIn("io.ktor.util.InternalAPI")
        }

        val ktorVersion = "2.0.0"
        val koin_version= "1.6.7"
        val commonMain by getting{
            dependencies {
                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies{
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.5")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation ("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight{
    database("NoteDatabase"){
        packageName = "com.example.kmpsninotes.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.example.kmpsninotes"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}