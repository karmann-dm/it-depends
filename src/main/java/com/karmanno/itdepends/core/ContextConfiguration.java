package com.karmanno.itdepends.core;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface ContextConfiguration {
    Map<String, ContextModule> getModules();
    Optional<ContextModule> registerModule(String id);
    Collection<PropertySource> getPropertySources();

    void registerPropertySource(PropertySource propertySource);
}
