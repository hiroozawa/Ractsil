// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpaths.gradle_plugin)
        classpath(Classpaths.kotlin_gradle_plugin)
        classpath(Classpaths.nav_safe_args)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

subprojects {
    apply(from = "$rootDir/versions.gradle.kts")
}

tasks.register("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
