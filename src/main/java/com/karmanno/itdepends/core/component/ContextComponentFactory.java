package com.karmanno.itdepends.core.component;

public interface ContextComponentFactory<T> {
    T create(Object... arguments);
}
