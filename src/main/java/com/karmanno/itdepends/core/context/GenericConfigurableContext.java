package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.*;
import java.util.stream.Stream;

public class GenericConfigurableContext implements ConfigurableContext {

    private ContextConfiguration contextConfiguration = new GenericContextConfiguration();

    @Override
    public ContextConfiguration getConfiguration() {
        return contextConfiguration;
    }

    @Override
    public GenericConfigurableContext configure(ConfigurationBuilder builder) {
        contextConfiguration = builder.build();
        return this;
    }

    @Override
    public Optional<ContextComponent<?>> getComponentById(String id) {
        return Optional.ofNullable(contextConfiguration.getComponents().get(id));
    }

    @Override
    public <T> Optional<ContextComponent<?>> getComponentByClass(Class<T> cls) {
        return contextConfiguration.getComponents()
                .values()
                .stream()
                .filter(contextComponent -> contextComponent.componentClass().equals(cls))
                .findFirst();
    }

    @Override
    public <T> Stream<ContextComponent<T>> getComponentsByClass(Class<T> cls) {
        return null;
    }

    @Override
    public <T> Optional<T> getInstanceByClass(Class<T> cls) {
        return (Optional<T>) contextConfiguration.getInstances()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getContextComponent().componentClass().equals(cls))
                .map(entry -> entry.getValue().getInstance())
                .findFirst();
    }

    @Override
    public void resolve() {

    }
}
