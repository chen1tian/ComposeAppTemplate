pluginManagement {
    repositories {

        maven { setUrl("https://jitpack.io") }

        maven {
            setUrl("http://nexus.dev.welldone.com/repository/maven-releases")
            isAllowInsecureProtocol = true
        }

        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }

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

        maven { setUrl("https://jitpack.io") }

        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }

        maven {
            setUrl("http://nexus.dev.welldone.com/repository/maven-releases")
            isAllowInsecureProtocol = true
        }

        google()
        mavenCentral()
    }
}

rootProject.name = "ComposeAppTemplate"
include(":app")
 