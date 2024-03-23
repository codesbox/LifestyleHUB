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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LifestyleHUB"
include(":app")
include(":features:weather")
include(":data:weather")
include(":data:recommendations_feed")
include(":features:recommendations_feed")
include(":common")
include(":features:details")
include(":data:details")
include(":features:auth:sign_up")
include(":features:auth:sign_in")
include(":features:auth:profile")
include(":data:auth")
include(":features:planner")
include(":data:planner")
