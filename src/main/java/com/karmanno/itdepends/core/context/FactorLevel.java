package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.candidate.ComponentCandidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactorLevel {
    private final int factor;
    private final List<ComponentCandidate<?>> unresolved = new ArrayList<>();
    private final Map<Class<?>, ComponentCandidate<?>> resolved = new HashMap<>();

    public FactorLevel(int factor) {
        this.factor = factor;
    }

    public void add(ComponentCandidate<?> candidate) {
        unresolved.add(candidate);
    }

    public List<ComponentCandidate<?>> getUnresolved() {
        return unresolved;
    }

    public Map<Class<?>, ComponentCandidate<?>> getResolved() {
        return resolved;
    }

    public int getFactor() {
        return factor;
    }
}
