package com.karmanno.itdepends.core.candidate;

import com.karmanno.itdepends.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class DefaultCandidateFactory<T> implements CandidateFactory<T> {
    private static final Logger logger = LoggerFactory.getLogger(DefaultCandidateFactory.class);
    private final ComponentCandidate<T> componentCandidate;
    private final Class<?>[] dependencyClasses;

    public DefaultCandidateFactory(ComponentCandidate<T> componentCandidate, Class<?>[] dependencyClasses) {
        this.componentCandidate = componentCandidate;
        this.dependencyClasses = dependencyClasses;
    }

    @Override
    public T create(Context context, Object... args) {
        checkTypeMismatch(args, dependencyClasses);

        try {
            var componentConstructor = componentCandidate.getCandidateConstructor();
            componentConstructor.setAccessible(true);
            return componentConstructor.newInstance(args);
        } catch (InstantiationException e) {
            logger.error("Failed to instantiate object of class {}", componentCandidate.getCandidateClass().getCanonicalName(), e);
        } catch (InvocationTargetException e) {
            logger.error("Failed to invoke constructor of class {}", componentCandidate.getCandidateClass().getCanonicalName(), e);
        } catch (IllegalAccessException e) {
            logger.error("Failed to get access to constructor of class {}", componentCandidate.getCandidateClass().getCanonicalName(), e);
        }

        throw new RuntimeException("Failed to create instance of candidate");
    }

    void checkTypeMismatch(Object[] arguments, Class<?>[] argumentClasses) {
        if (arguments == null)
            throw new RuntimeException("Arguments array is null");
        if (arguments.length != argumentClasses.length)
            throw new RuntimeException("Argument lengths mismatch");

        for (int argIndex = 0; argIndex < arguments.length; argIndex++) {
            var requiredArgType = argumentClasses[argIndex];
            var actualArgType = arguments[argIndex] == null ? Object.class : arguments[argIndex].getClass();
            if (!requiredArgType.isAssignableFrom(actualArgType)) {
                throw new RuntimeException("Type mismatch on arg position " + argIndex + " class " +
                        actualArgType.toString() + " is not compatible with required type " + requiredArgType.toString() +
                        " provided by constructor"
                );
            }
        }
    }
}
