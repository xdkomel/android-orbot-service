pluginManagement {
    val properties = java.util.Properties().apply {
        file("gradle.properties").inputStream().use(::load)
    }
    val authToken: String? = properties.getProperty("authToken")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { 
            url = uri("https://jitpack.io")
            credentials { username = authToken ?: "" }
        }
    }
}
dependencyResolutionManagement {
    val properties = java.util.Properties().apply {
        file("gradle.properties").inputStream().use(::load)
    }
    val authToken: String? = properties.getProperty("authToken")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { 
            url = uri("https://jitpack.io")
            credentials { username = authToken ?: "" }
        }
        maven { 
            url = uri("https://raw.githubusercontent.com/guardianproject/gpmaven/master") 
        }
    }
}

rootProject.name = "orbot_service_publisher"
include(":app")
include(":orbotservice")
 