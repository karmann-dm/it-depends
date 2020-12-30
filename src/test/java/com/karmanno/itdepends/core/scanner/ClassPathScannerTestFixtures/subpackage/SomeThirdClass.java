package com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures.subpackage;

import com.karmanno.itdepends.core.ContextComponent;

@ContextComponent
public class SomeThirdClass {
    private final Short state;

    public SomeThirdClass(Short state) {
        this.state = state;
    }

    public Short getState() {
        return state;
    }
}
