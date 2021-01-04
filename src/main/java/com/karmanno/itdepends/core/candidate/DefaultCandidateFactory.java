package com.karmanno.itdepends.core.candidate;

import com.karmanno.itdepends.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DefaultCandidateFactory<T> implements CandidateFactory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCandidateFactory.class);
    private final Class<T> candidateClass;
    private final Class<?>[] dependencyClasses;

    public DefaultCandidateFactory(Class<T> candidateClass, Class<?>[] dependencyClasses) {
        this.candidateClass = candidateClass;
        this.dependencyClasses = dependencyClasses;
    }

    @Override
    public T create(Context context, Object... args) {
        checkTypeMismatch(args, dependencyClasses);

        try {
            Constructor<T> componentConstructor;
            if (dependencyClasses.length == 0)
                componentConstructor = candidateClass.getDeclaredConstructor();
            else
                componentConstructor = candidateClass.getDeclaredConstructor(dependencyClasses);
            componentConstructor.setAccessible(true);
            return componentConstructor.newInstance(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    void checkTypeMismatch(Object[] arguments, Class<?>[] argumentClasses) {
        if (arguments == null)
            throw new RuntimeException("Arguments array is null");
        if (arguments.length != argumentClasses.length)
            throw new RuntimeException("Argument lengths mismatch");
        for (int argIndex = 0; argIndex < arguments.length; argIndex++) {
            Class<?> requiredArgType = argumentClasses[argIndex];
            Class<?> actualArgType = arguments[argIndex] == null ? Object.class : arguments[argIndex].getClass();
            if (!requiredArgType.isAssignableFrom(actualArgType)) {
                throw new RuntimeException("Type mismatch on arg position " + argIndex + " class " +
                        actualArgType.toString() + " is not compatible with required type " + requiredArgType.toString() +
                        " provided by constructor"
                );
            }
        }
    }
}
