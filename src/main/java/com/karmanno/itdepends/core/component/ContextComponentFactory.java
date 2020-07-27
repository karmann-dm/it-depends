package com.karmanno.itdepends.core.component;

public interface ContextComponentFactory<T> {
    Class<?>[] argumentClasses();
    T create(Object... arguments);
}
