plugins {
    id("org.jetbrains.kotlin.jvm") version "2.4.10"
    id("application")
    id("com.android.application") version "9.4.0" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
}

application {
    mainClass.set("com.example.tenantmanagementsystemgroupa.MainKt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    sourceSets.main {
        kotlin.srcDir("src")
    }
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}
