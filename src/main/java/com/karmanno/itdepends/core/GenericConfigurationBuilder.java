package com.karmanno.itdepends.core;

public class GenericConfigurationBuilder implements ConfigurationBuilder {
    private final ContextConfiguration contextConfiguration = new GenericContextConfiguration();

    @Override
    public ConfigurationBuilder propertySource(PropertySource propertySource) {
        contextConfiguration.registerPropertySource(propertySource);
        return this;
    }

    @Override
    public ConfigurationBuilder module(String id) {
        contextConfiguration.registerModule(id)
                .orElseThrow(() -> new RuntimeException("Could not register module"));
        return this;
    }

    @Override
    public ConfigurationBuilder module(String id, GenericModuleBuilder genericModuleBuilder) {
        return null;
    }

    @Override
    public ContextConfiguration build() {
        return contextConfiguration;
    }
}
