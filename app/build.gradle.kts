import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.androidApplication)
    id(Plugins.safeArgs)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtension)
    kotlin(Plugins.kotlinKapt)
}

val javaVersion: JavaVersion by extra { JavaVersion.VERSION_1_8 }


android {
    compileSdkVersion(extra["compileSdkVersion"] as Int)
    defaultConfig {
        applicationId = "com.hiroozawa.ractsil"
        minSdkVersion(extra["minSdkVersion"] as Int)
        targetSdkVersion(extra["targetSdkVersion"] as Int)
        versionCode = 1
        versionName = getSemanticAppVersionName()
        testInstrumentationRunner = "com.hiroozawa.ractsil.MockTestRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("hiro_ractsil.jks")
            storePassword = "123123"
            keyAlias = "hiro"
            keyPassword = "123123"
        }
    }

    buildTypes {
        getByName("release") {
            buildConfigField("String", "SERVER_URL", "\"https://cdn.sixt.io/\"")
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        getByName("debug") {
            buildConfigField("String", "SERVER_URL", "\"https://cdn.sixt.io/\"")
            isMinifyEnabled = false
            isDebuggable = true
        }
    }

    sourceSets {
        val main by getting
        main.java.srcDirs("src/main/kotlin")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    androidExtensions {
        isExperimental = true
    }

    tasks.withType<JavaCompile> {
        options.isIncremental = true
        allprojects {
            options.compilerArgs.addAll(
                arrayOf(
                    "-Xlint:-unchecked",
                    "-Xlint:deprecation",
                    "-Xdiags:verbose"
                )
            )
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
            allWarningsAsErrors = true
            freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_gradle_plugin}")
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}")
    implementation("androidx.core:core-ktx:${Versions.core_ktx}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.annotation:annotation:${Versions.android_annotation}")
    implementation("com.google.android.gms:play-services-maps:${Versions.play_services}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.0")

    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    implementation("io.coil-kt:coil:0.9.2")

    // AndroidX Test - JVM testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0")
    testImplementation("androidx.arch.core:core-testing:2.0.0")
    testImplementation("androidx.test:core-ktx:1.2.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.1")
    testImplementation("androidx.test:rules:1.2.0")
    testImplementation("androidx.test:core:${Versions.test_core}")
    testImplementation("androidx.test.espresso:espresso-core:${Versions.espresso_core}")
    implementation("androidx.test.espresso:espresso-idling-resource:${Versions.espresso_core}")


    // Android Test
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso_core}")
    androidTestImplementation("androidx.test.espresso.idling:idling-concurrent:${Versions.espresso_core}")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:${Versions.okhttp}")
    // Once https://issuetracker.google.com/127986458 is fixed this can be testImplementation
    debugImplementation("androidx.fragment:fragment-testing:1.2.0")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:${Versions.espresso_core}")

    // Dagger
    implementation("com.google.dagger:dagger:${Versions.dagger}")
    kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
    implementation("com.google.dagger:dagger-android-support:${Versions.dagger}")
    kapt("com.google.dagger:dagger-android-processor:${Versions.dagger}")
    // Using Dagger in androidTest
    kaptAndroidTest("com.google.dagger:dagger-compiler:${Versions.dagger}")
    kaptTest("com.google.dagger:dagger-compiler:${Versions.dagger}")

    // Remote
    implementation("com.google.code.gson:gson:${Versions.gson}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.okhttp}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}")
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofit}")
}
fun getSemanticAppVersionName(): String {
    val majorCode = 1
    val minorCode = 0
    val patchCode = 0

    return "$majorCode.$minorCode.$patchCode"
}

