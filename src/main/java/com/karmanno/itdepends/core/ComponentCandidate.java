package com.karmanno.itdepends.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 */
public class ComponentCandidate<T> {
    private final Class<T> candidateClass;
    private final List<Function<T, T>> postProcessors = new ArrayList<>();
    private Supplier<T> factory;

    public ComponentCandidate(Class<T> cls) {
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
}
