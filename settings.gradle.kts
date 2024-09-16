pluginManagement {
    repositories {
        google()
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
        maven { 
            url = uri("https://jitpack.io")
        }
        maven { 
            url = uri("https://raw.githubusercontent.com/guardianproject/gpmaven/master") 
        }
    }
}

rootProject.name = "orbot_service_publisher"
include(":app")
include(":orbotservice")
 