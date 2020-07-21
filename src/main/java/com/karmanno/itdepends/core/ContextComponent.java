package com.karmanno.itdepends.core;

public interface ContextComponent {
    Class<?> componentClass();
    ContextComponentFactory<?> componentFactory();
}
