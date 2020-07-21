package com.karmanno.itdepends.core;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class GenericContextConfiguration implements ContextConfiguration {
    Map<String, ContextModule> contextModuleMap = new ConcurrentHashMap<>();
    Map<String, PropertySource> propertySourceMap = new ConcurrentHashMap<>();

    @Override
    public Map<String, ContextModule> getModules() {
        return contextModuleMap;
    }

    @Override
    public Optional<ContextModule> registerModule(String id) {
        if (contextModuleMap.containsKey(id))
            return Optional.empty();
        contextModuleMap.put(id, new GenericModuleBuilder().build());
        return Optional.ofNullable(contextModuleMap.get(id));
    }

    @Override
    public Collection<PropertySource> getPropertySources() {
        return propertySourceMap.values();
    }

    @Override
    public void registerPropertySource(PropertySource propertySource) {
        propertySourceMap.put(propertySource.getPath().toString(), propertySource);
    }
}
