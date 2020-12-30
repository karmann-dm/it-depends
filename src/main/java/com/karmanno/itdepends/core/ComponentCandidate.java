package com.karmanno.itdepends.core;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Generic class representing a candidate for component to add in context
 */
public class ComponentCandidate<T> {
    private final Class<T> candidateClass;
    private final List<Function<T, T>> postProcessors = new ArrayList<>();
    private Supplier<T> factory;

    public ComponentCandidate(@Nonnull Class<T> cls) {
        this.candidateClass = cls;
    }

    public void setFactory(Supplier<T> factory) {
        this.factory = factory;
    }

    public ComponentCandidate<T> post(Function<T, T> postProcessor) {
        postProcessors.add(postProcessor);
        return this;
    }

    public Supplier<T> getFactory() {
        return factory;
    }

    public Class<T> getCandidateClass() {
        return candidateClass;
    }

    public List<Function<T, T>> getPostProcessors() {
        return postProcessors;
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
}
