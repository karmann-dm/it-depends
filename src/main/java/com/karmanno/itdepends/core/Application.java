package com.karmanno.itdepends.core;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Application {
    Class<? extends Scanner>[] scanners();
    String[] classPathScanner();
    String[] propertiesScanner();
}
