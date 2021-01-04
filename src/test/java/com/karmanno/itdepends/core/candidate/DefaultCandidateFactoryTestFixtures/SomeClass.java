package com.karmanno.itdepends.core.candidate.DefaultCandidateFactoryTestFixtures;

import java.util.List;
import java.util.Map;

public class SomeClass {
    private final String string;
    private final List<Integer> integers;
    private final Map<Double, Short> map;

    public SomeClass(String string, List<Integer> integers, Map<Double, Short> map) {
        this.string = string;
        this.integers = integers;
        this.map = map;
    }

    public List<Integer> getIntegers() {
        return integers;
    }

    public Map<Double, Short> getMap() {
        return map;
    }

    public String getString() {
        return string;
    }
}
