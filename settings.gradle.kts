pluginManagement {
    val properties = java.util.Properties().apply {
        file("gradle.properties").inputStream().use(::load)
    }
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://raw.githubusercontent.com/guardianproject/gpmaven/master")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    val properties = java.util.Properties().apply {
        file("gradle.properties").inputStream().use(::load)
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { 
            url = uri("https://raw.githubusercontent.com/guardianproject/gpmaven/master") 
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "orbot_service_publisher"
include(":app")
include(":orbotservice")