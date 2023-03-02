@file:Suppress("unused")

package com.github.minimi.platformagnostic.inject

actual interface Provider<T> {

    actual fun get(): T
}
