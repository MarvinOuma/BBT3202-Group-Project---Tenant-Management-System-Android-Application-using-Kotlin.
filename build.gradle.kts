plugins {
    // Keep the Kotlin plugin version used by the tested Android baseline.
    id("org.jetbrains.kotlin.jvm") version "2.4.10" apply false
    id("org.jetbrains.kotlin.android") version "2.4.10" apply false
    id("com.android.application") version "8.5.2" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
    // KSP generates Room's database code while building
    id("com.google.devtools.ksp") version "2.3.0" apply false
}
