object Dependencies {

    //FileTree
    val fileTree = mapOf("dir" to "libs", "include" to listOf("*.jar"))

    //Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    //AppCompat
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    // Material Components & ConstraintLayout
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    //Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //ViewModel & LiveData
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}"
    const val lifeCycleKpt = "androidx.lifecycle:lifecycle-compiler:${Versions.lifeCycle}"

    //Navigation
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    //Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltLifeCycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifeCycle}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltLifeCycleCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltLifeCycle}"

    // Testing
    const val testCore = "androidx.test:core:${Versions.testCore}"
    const val kluent = "org.amshove.kluent:kluent-android:${Versions.kluent}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val liveDataTest = "androidx.arch.core:core-testing:${Versions.liveDataTest}"
}