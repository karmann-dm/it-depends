package com.karmanno.itdepends.core;

import java.util.Collection;
import java.util.Map;

public interface ContextConfiguration {
    ContextComponent<?> registerComponent(ContextComponent<?> contextComponent);

    Collection<PropertySource> getPropertySources();

    void registerPropertySource(PropertySource propertySource);

    Map<String, ContextComponent<?>> getComponents();
}
