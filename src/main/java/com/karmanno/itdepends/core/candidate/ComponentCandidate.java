package com.karmanno.itdepends.core.candidate;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Generic class representing a candidate for component to add in context
 */
public class ComponentCandidate<T> {
    private final Class<T> candidateClass;
    private CandidateFactory<T> factory;

    public ComponentCandidate(@Nonnull Class<T> cls) {
        this.candidateClass = cls;
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
