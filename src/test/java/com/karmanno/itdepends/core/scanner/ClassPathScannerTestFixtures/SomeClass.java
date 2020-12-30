package com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures;

import com.karmanno.itdepends.core.ContextComponent;

import java.util.List;

@ContextComponent
public class SomeClass {
    private final List<String> state;

    public SomeClass(List<String> state) {
        this.state = state;
    }

    public List<String> getState() {
        return state;
    }
}
