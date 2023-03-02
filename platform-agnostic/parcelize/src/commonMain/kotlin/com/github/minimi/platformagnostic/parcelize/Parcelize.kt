@file:Suppress("KDocUnresolvedReference")

package com.github.minimi.platformagnostic.parcelize

/**
 * Instructs the Kotlin compiler to generate `writeToParcel()`, `describeContents()` [android.os.Parcelable] methods,
 * as well as a `CREATOR` factory class automatically.
 *
 * The annotation is applicable only to classes that implements [android.os.Parcelable] (directly or indirectly).
 * Note that only the primary constructor properties will be serialized.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class Parcelize()