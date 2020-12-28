package com.karmanno.itdepends.core;

import java.util.stream.Stream;

/**
 * Common interface which is representing a DI container
 */
public interface Context {
    /**
     * Pulls component instances in lazy stream for the specified class
     * @param componentClass Class, provided as desirable component instance type
     * @param <T> Type of the desirable component instance
     * @return Stream of component instances
     */
    <T> Stream<T> pull(Class<T> componentClass);

    /**
     * Resolves unresolved dependencies of an object
     * @param resolvable An object with the dependencies which are need to resolve
     * @param <T> Type of an object to inject the dependencies
     * @return An object with resolved dependencies
     * @throws
     */
    <T> T resolve(T resolvable);
}
