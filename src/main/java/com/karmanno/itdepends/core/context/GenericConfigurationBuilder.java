package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ContextComponentBuilder;
import com.karmanno.itdepends.core.property.PropertySourceComponent;

public class GenericConfigurationBuilder implements ConfigurationBuilder {
    private final ContextConfiguration contextConfiguration = new GenericContextConfiguration();

    @Override
    public GenericConfigurationBuilder propertySource(String path) {
        contextConfiguration.registerPropertySource(path);
        return this;
    }

    @Override
    public GenericConfigurationBuilder component(ContextComponentBuilder<?> componentBuilder) {
        contextConfiguration.registerComponent(componentBuilder.build());
        return this;
    }

    @Override
    public ContextConfiguration build() {
        return contextConfiguration;
    }
}
