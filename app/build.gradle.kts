plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kapt)
    id(Plugins.navSafeArgs)
    id(Plugins.hiltAndroid)
}

android {
    compileSdkVersion(Project.Android.compileSdkVersion)
    buildToolsVersion = Project.Android.buildToolsVersion

    defaultConfig {
        applicationId = Project.Android.applicationId
        minSdkVersion(Project.Android.minSdkVersion)
        targetSdkVersion(Project.Android.targetSdkVersion)
        versionCode = Project.Android.versionCode
        versionName = Project.Android.versionName

        vectorDrawables.useSupportLibrary = Project.Android.useSupportLibrary

        testInstrumentationRunner(Project.Android.androidRunner)
    }

    buildTypes {
        getByName(Project.BuildType.release) {
            isMinifyEnabled = Project.BuildType.enableMinify
            proguardFiles(
                getDefaultProguardFile(Project.Proguard.file), Project.Proguard.rules
            )
        }

        getByName(Project.BuildType.debug) {
            isMinifyEnabled = !Project.BuildType.enableMinify
            proguardFiles(
                getDefaultProguardFile(Project.Proguard.file), Project.Proguard.rules
            )
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(Dependencies.fileTree))

    implementation(project(Project.data))
    implementation(project(Project.domain))

    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coreKtx)

    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    implementation(Dependencies.navigation)
    implementation(Dependencies.navigationKtx)

    implementation(Dependencies.viewModel)
    implementation(Dependencies.liveData)
    kapt(Dependencies.lifeCycleKpt)

    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltLifeCycle)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltLifeCycleCompiler)

    testImplementation(Dependencies.liveDataTest)
    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.junitExt)
    androidTestImplementation(Dependencies.espressoCore)
}