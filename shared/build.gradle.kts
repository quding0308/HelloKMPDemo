import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsComposeMultiplatform)
    alias(libs.plugins.kotlinComposeCompiler)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvmToolchain(11)
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }

        // 文档 https://kotlinlang.org/docs/native-cocoapods-libraries.html
//        pod("FirebaseAuth") {
//            version = "10.16.0"
//        }
        pod("SnapKit") {
            version = "5.7.1"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }
    
    sourceSets {
        commonMain.dependencies {
//            implementation(projects.shared)

            implementation(projects.kmpLib)

            //put your multiplatform dependencies here
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.jetbrains.androidx.lifecycle.viewmodel)
            implementation(libs.jetbrains.androidx.lifecycle.runtime.compose)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation("network.chaintech:compose-multiplatform-media-player:1.0.27")
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "shared.generated.resources"
    generateResClass = auto
}

android {
    namespace = "com.example.hellokmpdemo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

//dependencies {
//    implementation(libs.androidx.compose.material3.android)
//    debugImplementation(compose.uiTooling)
//}
