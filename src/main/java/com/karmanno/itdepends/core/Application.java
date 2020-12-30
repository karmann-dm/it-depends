package com.karmanno.itdepends.core;

import com.karmanno.itdepends.core.scanner.Scanner;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Application {
    Class<? extends Scanner>[] scanners();
    String[] classPathScanner();
    String[] propertiesScanner();
}
