pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RetoTecnicoApp"
include(":app")
include(":core")
include(":manager")
include(":core:navigation")
include(":core:designsystem")
include(":manager:presentation")
include(":core:domain")
include(":manager:domain")
include(":manager:infrastructure")
