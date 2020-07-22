package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.ConfigurationBuilder;
import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.Optional;
import java.util.stream.Stream;

public interface ConfigurableContext {
    ContextConfiguration getConfiguration();
    ContextConfiguration configure(ConfigurationBuilder builder);


    Optional<ContextComponent<?>> getComponentById(String id);

    <T> Optional<ContextComponent<?>> getComponentByClass(Class<T> cls);

    <T> Stream<ContextComponent<T>> getComponentsByClass(Class<T> cls);
}
