package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.context.ContextConfiguration;
import com.karmanno.itdepends.core.property.PropertySource;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericContextConfiguration implements ContextConfiguration {
    Map<String, PropertySource> propertySourceMap = new ConcurrentHashMap<>();
    Map<String, ContextComponent<?>> components = new ConcurrentHashMap<>();
    Map<String, ComponentInstance<?>> instances = new ConcurrentHashMap<>();

    // TODO: resolve conflicts
    @Override
    public ContextComponent<?> registerComponent(ContextComponent<?> contextComponent) {
        components.put(contextComponent.getClass().getCanonicalName(), contextComponent);
        return contextComponent;
    }

    @Override
    public Collection<PropertySource> getPropertySources() {
        return propertySourceMap.values();
    }

    @Override
    public void registerPropertySource(PropertySource propertySource) {
        propertySourceMap.put(propertySource.getPath().toString(), propertySource);
    }

    @Override
    public Map<String, ContextComponent<?>> getComponents() {
        return components;
    }

    @Override
    public Map<String, ComponentInstance<?>> getInstances() {
        return instances;
    }
}
