pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// Group member admission numbers: 147423, 175716, 190164, 177292, 173572, 193425.
rootProject.name = "TenantManagementSystem"
include(":app")
