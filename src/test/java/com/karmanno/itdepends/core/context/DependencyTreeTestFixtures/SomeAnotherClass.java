package com.karmanno.itdepends.core.context.DependencyTreeTestFixtures;

import java.util.Map;

public class SomeAnotherClass {
    private final Double aDouble;
    private final String string;
    private final Map<Integer, String> map;

    public SomeAnotherClass(Double aDouble, String string, Map<Integer, String> map) {
        this.aDouble = aDouble;
        this.string = string;
        this.map = map;
    }
}
