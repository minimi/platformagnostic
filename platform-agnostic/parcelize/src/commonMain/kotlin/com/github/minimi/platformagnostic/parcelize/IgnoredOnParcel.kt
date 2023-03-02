package com.github.minimi.platformagnostic.parcelize

/**
 * The property annotated with [IgnoredOnParcel] will not be stored into parcel.
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
expect annotation class IgnoredOnParcel