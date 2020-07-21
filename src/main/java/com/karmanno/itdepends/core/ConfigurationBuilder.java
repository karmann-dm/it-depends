package com.karmanno.itdepends.core;

public interface ConfigurationBuilder {
    ConfigurationBuilder propertySource(PropertySource propertySource);
    ConfigurationBuilder component(ContextComponentBuilder<?> componentBuilder);
    ContextConfiguration build();
}
