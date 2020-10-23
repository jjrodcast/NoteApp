plugins {
    id(Plugins.javaLibrary)
    id(Plugins.kotlin)
    kotlin(Plugins.kapt)
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.kotlinStdLib)
    implementation(Dependencies.coreKtx)
    api(Dependencies.coroutines)


    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
}