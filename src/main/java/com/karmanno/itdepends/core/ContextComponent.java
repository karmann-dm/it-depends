package com.karmanno.itdepends.core;

public interface ContextComponent<T> {
    Class<T> componentClass();
    ContextComponentFactory<T> componentFactory();
    Scope scope();
}
