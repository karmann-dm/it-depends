package com.karmanno.itdepends.core;

public class GenericConfigurationBuilder implements ConfigurationBuilder {
    private final ContextConfiguration contextConfiguration = new GenericContextConfiguration();

    @Override
    public ConfigurationBuilder propertySource(PropertySource propertySource) {
        contextConfiguration.registerPropertySource(propertySource);
        return this;
    }

    @Override
    public ConfigurationBuilder component(ContextComponentBuilder<?> componentBuilder) {
        contextConfiguration.registerComponent(componentBuilder.build());
        return this;
    }

    @Override
    public ContextConfiguration build() {
        return contextConfiguration;
    }
}
