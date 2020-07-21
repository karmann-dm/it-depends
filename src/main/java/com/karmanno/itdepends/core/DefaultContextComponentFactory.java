package com.karmanno.itdepends.core;

import java.lang.reflect.Constructor;

public class DefaultContextComponentFactory implements ContextComponentFactory<Object> {
    private final Object[] arguments;
    private final Class<?>[] argumentClasses;
    private final Class<?> componentClass;

    public DefaultContextComponentFactory(Class<?> componentClass, Object... arguments) {
        this.arguments = arguments;
        this.componentClass = componentClass;
        argumentClasses = new Class<?>[arguments.length];
        for (int i = 0; i < argumentClasses.length; i++) {
            argumentClasses[i] = arguments[i].getClass();
        }
    }

    @Override
    public Object create() {
        try {
            Constructor<?> componentConstructor = componentClass.getDeclaredConstructor(argumentClasses);
            componentConstructor.setAccessible(true);
            return componentConstructor.newInstance(arguments);
        } catch (Exception e) {
            throw new RuntimeException("Could not instantiate component");
        }
    }
}
