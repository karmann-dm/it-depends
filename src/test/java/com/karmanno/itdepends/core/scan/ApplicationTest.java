package com.karmanno.itdepends.core.scan;

import org.junit.Test;

public class ApplicationTest {
    @Test
    public void shouldScan() {

    }

    @Application(basePackageToScan = "com.karmanno.itdepends.core.examples.appication_annotation_correct")
    static class AppScopedAnnotationClass {
    }
}
