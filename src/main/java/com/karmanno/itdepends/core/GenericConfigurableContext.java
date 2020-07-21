package com.karmanno.itdepends.core;

import java.util.Optional;
import java.util.stream.Stream;

public class GenericConfigurableContext implements ConfigurableContext {
    @Override
    public ContextConfiguration getConfiguration() {
        return null;
    }

    @Override
    public ContextConfiguration configure(ConfigurationBuilder builder) {
        return null;
    }

    @Override
    public Optional<ContextModule> register(ContextModule module) {
        return Optional.empty();
    }

    @Override
    public Optional<ContextComponent> getComponentById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<ContextComponent> getComponentById(String id, String moduleId) {
        return Optional.empty();
    }

    @Override
    public Optional<ContextComponent> getComponentByClass(Class<?> cls) {
        return Optional.empty();
    }

    @Override
    public Optional<ContextComponent> getComponentByClass(Class<?> cls, String moduleId) {
        return Optional.empty();
    }

    @Override
    public Stream<ContextComponent> getComponentsByClass(Class<?> cls) {
        return null;
    }
}
