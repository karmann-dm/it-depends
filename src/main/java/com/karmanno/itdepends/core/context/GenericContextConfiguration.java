package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.property.PropertySourceComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericContextConfiguration implements ContextConfiguration {
    Map<String, PropertySourceComponent> propertySourceMap = new ConcurrentHashMap<>();
    Map<String, ContextComponent<?>> components = new ConcurrentHashMap<>();
    Map<String, ComponentInstance<?>> instances = new ConcurrentHashMap<>();

    // TODO: resolve conflicts
    @Override
    public ContextComponent<?> registerComponent(ContextComponent<?> contextComponent) {
        components.put(contextComponent.id(), contextComponent);
        return contextComponent;
    }

    @Override
    public void registerPropertySource(String path) {

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
