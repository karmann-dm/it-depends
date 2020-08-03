package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.Optional;
import java.util.stream.Stream;

public interface ConfigurableContext {

    /**
     *
     * @return
     */
    ContextConfiguration getConfiguration();

    /**
     *
     * @param builder
     * @return
     */
    GenericConfigurableContext configure(ConfigurationBuilder builder);

    /**
     *
     * @param id
     * @return
     */
    Optional<ContextComponent<?>> getComponentById(String id);

    /**
     *
     * @param cls
     * @param <T>
     * @return
     */
    <T> Optional<ContextComponent<?>> getComponentByClass(Class<T> cls);

    /**
     *
     * @param cls
     * @param <T>
     * @return
     */
    <T> Stream<ContextComponent<T>> getComponentsByClass(Class<T> cls);


    <T> Optional<T> getInstanceByClass(Class<T> cls);

    /**
     * Main method for the start of context-resolving process
     */
    void resolve();
}
