package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.property.PropertySource;

import java.util.Collection;
import java.util.Map;

public interface ContextConfiguration {
    ContextComponent<?> registerComponent(ContextComponent<?> contextComponent);

    Collection<PropertySource> getPropertySources();

    void registerPropertySource(PropertySource propertySource);

    Map<String, ContextComponent<?>> getComponents();

    Map<String, ComponentInstance<?>> getInstances();
}
