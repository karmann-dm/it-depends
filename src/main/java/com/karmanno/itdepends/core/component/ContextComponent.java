package com.karmanno.itdepends.core.component;

public interface ContextComponent<T> {
    Class<T> componentClass();
    ContextComponentFactory<T> componentFactory();
    Scope scope();
    InstantiationPolicy instantiationPolicy();
}
