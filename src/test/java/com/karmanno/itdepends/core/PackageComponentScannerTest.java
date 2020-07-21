package com.karmanno.itdepends.core;

import com.karmanno.itdepends.core.scan.PackageComponentScanner;
import org.junit.Assert;
import org.junit.Test;


public class PackageComponentScannerTest {
    @Test
    public void shouldScanPackage() {
        PackageComponentScanner packageComponentScanner = new PackageComponentScanner("com.karmanno.itdepends.core.examples");
        var components = packageComponentScanner.scanForComponents();

        Assert.assertEquals(4, components.size());
    }
}
