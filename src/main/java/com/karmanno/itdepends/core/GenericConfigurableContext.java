package com.karmanno.itdepends.core;

import java.util.Optional;
import java.util.stream.Stream;

public class GenericConfigurableContext implements ConfigurableContext {
    private final GenericContextConfiguration contextConfiguration = new GenericContextConfiguration();

    @Override
    public ContextConfiguration getConfiguration() {
        return contextConfiguration;
    }

    @Override
    public ContextConfiguration configure(ConfigurationBuilder builder) {
        return builder.build();
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
}
