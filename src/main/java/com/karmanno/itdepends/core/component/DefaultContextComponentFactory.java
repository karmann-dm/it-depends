package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.exception.ComponentFactoryException;

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
        checkTypeMismatch(arguments);

        try {
            Constructor<T> componentConstructor = componentClass.getConstructor(argumentClasses);
            componentConstructor.setAccessible(true);
            return componentConstructor.newInstance(arguments);
        } catch (Exception e) {
            throw new ComponentFactoryException("Could not instantiate component", e);
        }
    }

    void checkTypeMismatch(Object... arguments) {
        if (arguments == null)
            throw new ComponentFactoryException("Arguments array is null");
        if (arguments.length != argumentClasses.length)
            throw new ComponentFactoryException("Argument lengths mismatch");
        for (int argIndex = 0; argIndex < arguments.length; argIndex++) {
            Class<?> requiredArgType = argumentClasses[argIndex];
            Class<?> actualArgType = arguments[argIndex] == null ? Object.class : arguments[argIndex].getClass();
            if (!requiredArgType.isAssignableFrom(actualArgType)) {
                throw new ComponentFactoryException("Type mismatch on arg position " + argIndex + " class " +
                            actualArgType.toString() + " is not compatible with required type " + requiredArgType.toString() +
                            " provided by constructor"
                        );
            }
        }
    }

    public Class<?>[] getArgumentClasses() {
        return argumentClasses;
    }

    public Class<T> getComponentClass() {
        return componentClass;
    }
}
