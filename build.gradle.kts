plugins {
    id ("com.android.library") version "7.4.1" apply false
    kotlin("multiplatform") version "1.8.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}