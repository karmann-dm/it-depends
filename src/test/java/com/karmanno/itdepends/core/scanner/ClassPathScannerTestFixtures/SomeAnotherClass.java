package com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures;

import com.karmanno.itdepends.core.ContextComponent;

@ContextComponent
public class SomeAnotherClass {
    private final Integer state;

    public SomeAnotherClass(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
