package com.karmanno.itdepends.core;

public interface ConfigurationBuilder {
    ConfigurationBuilder propertySource(PropertySource propertySource);
    ConfigurationBuilder module(String id);
    ConfigurationBuilder module(String id, GenericModuleBuilder genericModuleBuilder);
    ContextConfiguration build();
}
