package com.karmanno.itdepends.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class ComponentCandidate<T> {
    private final Class<T> candidateClass;
    private final List<Function<T, T>> preProcessors = new ArrayList<>();
    private final List<Function<T, T>> postProcessors = new ArrayList<>();

    public ComponentCandidate(Class<T> cls) {
        this.candidateClass = cls;
    }


}
