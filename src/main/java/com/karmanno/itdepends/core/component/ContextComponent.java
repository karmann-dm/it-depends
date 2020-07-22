package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.Scope;

public interface ContextComponent<T> {
    Class<T> componentClass();
    ContextComponentFactory<T> componentFactory();
    Scope scope();
}
