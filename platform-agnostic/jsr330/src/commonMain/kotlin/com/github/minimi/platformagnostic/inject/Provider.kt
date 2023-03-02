package com.github.minimi.platformagnostic.inject

/**
 * Provides instances of [T]. Typically implemented by an injector. For
 * any type [T] that can be injected, you can also inject
 * [Provider]<[T]>. Compared to injecting [T] directly, injecting
 * [Provider]<[T]> enables:
 *
 *   - retrieving multiple instances.</li>
 *   - lazy or optional retrieval of an instance.</li>
 *   - breaking circular dependencies.</li>
 *   - abstracting scope so you can look up an instance in a smaller scope
 *      from an instance in a containing scope.</li>
 *
 * For example:
 *
 * ```java
 *   class Car {
 *     @Inject Car(Provider<Seat> seatProvider) {
 *       Seat driver = seatProvider.get();
 *       Seat passenger = seatProvider.get();
 *       ...
 *     }
 *   }
 * ```
 */
expect interface Provider<T> {

    /**
     * Provides a fully-constructed and injected instance of {@code T}.
     *
     * @throws RuntimeException if the injector encounters an error while
     *  providing an instance. For example, if an injectable member on
     *  {@code T} throws an exception, the injector may wrap the exception
     *  and throw it to the caller of {@code get()}. Callers should not try
     *  to handle such exceptions as the behavior may vary across injector
     *  implementations and even different configurations of the same injector.
     */
    fun get(): T
}
