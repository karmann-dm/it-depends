package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.candidate.ComponentCandidate;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DependencyTree {
    private SortedMap<Integer, FactorLevel> factorLevels = new TreeMap<>();
    private Map<String, Integer> references = new HashMap<>();

    public void addDependency(ComponentCandidate<?> candidate) {
        if (!factorLevels.containsKey(candidate.getDependencyFactor())) {
            factorLevels.put(candidate.getDependencyFactor(), new FactorLevel(candidate.getDependencyFactor()));
        }
        factorLevels.get(candidate.getDependencyFactor()).add(candidate);
        references.put(candidate.getReference(), candidate.getDependencyFactor());
    }

    public SortedMap<Integer, FactorLevel> getFactorLevels() {
        return factorLevels;
    }

    public Map<String, Integer> getReferences() {
        return references;
    }
}
