package com.karmanno.itdepends.core.context.DependencyTreeTestFixtures;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SomeClass {
    private final String string;
    private final List<Integer> list;
    private final AtomicBoolean atomicBoolean;

    public SomeClass(String string, List<Integer> list, AtomicBoolean atomicBoolean) {
        this.string = string;
        this.list = list;
        this.atomicBoolean = atomicBoolean;
    }
}
