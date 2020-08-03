package com.karmanno.itdepends.core.examples.without_annotation;

import com.karmanno.itdepends.core.scan.ContextScoped;

import java.io.InputStream;

@ContextScoped
public class SomeFirst {
    public SomeFirst(InputStream stream) {}
}
