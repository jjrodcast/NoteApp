plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
}

android {
    compileSdkVersion(Project.Android.compileSdkVersion)
    buildToolsVersion(Project.Android.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Project.Android.minSdkVersion)
        targetSdkVersion(Project.Android.targetSdkVersion)
        versionCode(Project.Android.versionCode)
        versionName(Project.Android.versionName)

        testInstrumentationRunner = Project.Android.androidRunner
        consumerProguardFiles(Project.Proguard.rules)
    }

    buildTypes {
        getByName(Project.BuildType.release) {
            isMinifyEnabled = Project.BuildType.enableMinify
            proguardFiles(
                getDefaultProguardFile(Project.Proguard.file),
                Project.Proguard.rules
            )

            //Variables
            buildConfigField("int", "DB_VERSION", Project.Variables.databaseVersion)
            buildConfigField("String", "DATABASE_NAME", Project.Variables.databaseName)

        }
        getByName(Project.BuildType.debug) {
            isMinifyEnabled = !Project.BuildType.enableMinify
            proguardFiles(
                getDefaultProguardFile(Project.Proguard.file),
                Project.Proguard.rules
            )

            //Variables
            buildConfigField("int", "DB_VERSION", Project.Variables.databaseVersion)
            buildConfigField("String", "DATABASE_NAME", Project.Variables.databaseName)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(Dependencies.fileTree))

    implementation(project(Project.domain))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)

    api(Dependencies.room)
    api(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)

    testImplementation(Dependencies.junit)
}