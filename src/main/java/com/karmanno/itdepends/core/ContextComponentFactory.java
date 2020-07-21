package com.karmanno.itdepends.core;

public interface ContextComponentFactory<T> {
    T create(Object... arguments);
}
