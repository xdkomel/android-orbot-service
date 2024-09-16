pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { 
            url = uri("https://jitpack.io")
            credentials { username = System.getProperty("authToken") }
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
            credentials { username = System.getProperty("authToken") }
        }
        maven { 
            url = uri("https://raw.githubusercontent.com/guardianproject/gpmaven/master") 
        }
    }
}

rootProject.name = "orbot_service_publisher"
include(":app")
include(":orbotservice")
 