object Project {

    const val app = ":app"
    const val data = ":data"
    const val domain = ":domain"

    object Classpath {
        const val toolsBuildGradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinGradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val navSafeArgs =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navSafeArgs}"

        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }

    object Task {
        const val clean = "clean"
    }

    object Android {
        const val compileSdkVersion = 29
        const val buildToolsVersion = "30.0.2"
        const val applicationId = "com.jjrodcast.note"
        const val minSdkVersion = 28
        const val targetSdkVersion = 30
        const val versionCode = 1
        const val versionName = "1.0.0"
        const val androidRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val useSupportLibrary = true
    }

    object BuildType {
        const val release = "release"
        const val debug = "debug"
        const val enableMinify = true
    }

    object Proguard {
        const val file = "proguard-android-optimize.txt"
        const val rules = "proguard-rules.pro"
    }

    object Variables {
        const val databaseVersion = "1"
        const val databaseName = "\"notes_db\""
    }
}