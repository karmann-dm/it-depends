package com.karmanno.itdepends.core;

import java.lang.reflect.Constructor;

public class DefaultContextComponentFactory<T> implements ContextComponentFactory<T> {
    private final Class<?>[] argumentClasses;
    private final Class<T> componentClass;

    public DefaultContextComponentFactory(Class<T> componentClass, Class<?>... argumentClasses) {
        this.componentClass = componentClass;
        this.argumentClasses = argumentClasses;
    }

    @Override
    public T create(Object... arguments) {
        try {
            Constructor<T> componentConstructor = componentClass.getConstructor(argumentClasses);
            componentConstructor.setAccessible(true);
            return componentConstructor.newInstance(arguments);
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate component", e);
        }
    }
}
