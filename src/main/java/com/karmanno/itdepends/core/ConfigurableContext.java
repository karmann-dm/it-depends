package com.karmanno.itdepends.core;

import java.util.Optional;
import java.util.stream.Stream;

public interface ConfigurableContext {
    ContextConfiguration getConfiguration();
    ContextConfiguration configure(ConfigurationBuilder builder);

    Optional<ContextModule> register(ContextModule module);

    Optional<ContextComponent> getComponentById(String id);
    Optional<ContextComponent> getComponentById(String id, String moduleId);

    Optional<ContextComponent> getComponentByClass(Class<?> cls);
    Optional<ContextComponent> getComponentByClass(Class<?> cls, String moduleId);

    Stream<ContextComponent> getComponentsByClass(Class<?> cls);
}
