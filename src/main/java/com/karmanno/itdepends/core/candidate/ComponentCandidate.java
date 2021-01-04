package com.karmanno.itdepends.core.candidate;

import com.karmanno.itdepends.core.ToInject;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Generic class representing a candidate for component to add in context
 */
public class ComponentCandidate<T> {
    private final Class<T> candidateClass;
    private final Constructor<T> candidateConstructor;
    private final int dependencyFactor;
    private final String reference;
    private CandidateFactory<T> factory;
    private boolean resolved = false;

    public ComponentCandidate(@Nonnull Class<T> cls) {
        this.candidateClass = cls;
        candidateConstructor = findConstructor();
        dependencyFactor = candidateConstructor.getParameterCount();
        reference = UUID.randomUUID().toString();
    }

    private Constructor<T> findConstructor() {
        var annotatedConstructors = Arrays.stream(candidateClass.getConstructors())
                .filter(constructor -> constructor.isAnnotationPresent(ToInject.class))
                .collect(Collectors.toList());
        if (annotatedConstructors.size() > 1) {
            throw new RuntimeException("There can be no more than one constructor with @ToInject annotation");
        } else if (annotatedConstructors.isEmpty()) {
            var allConstructors = candidateClass.getConstructors();
            if (allConstructors.length > 1) {
                throw new RuntimeException("Although there are no annotated constructors - there can be only one constructor for dependency injection");
            } else {
                return (Constructor<T>) allConstructors[0];
            }
        } else {
            return (Constructor<T>) annotatedConstructors.get(0);
        }
    }

    public void setFactory(CandidateFactory<T> factory) {
        this.factory = factory;
    }

    public CandidateFactory<T> getFactory() {
        return factory;
    }

    public Class<T> getCandidateClass() {
        return candidateClass;
    }

    public Constructor<T> getCandidateConstructor() {
        return candidateConstructor;
    }

    public int getDependencyFactor() {
        return dependencyFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComponentCandidate<?> that = (ComponentCandidate<?>) o;
        return candidateClass.equals(that.candidateClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateClass);
    }

    public void resolve() {
        this.resolved = true;
    }

    public boolean isResolved() {
        return resolved;
    }

    public String getReference() {
        return reference;
    }
}
