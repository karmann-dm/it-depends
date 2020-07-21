package com.karmanno.itdepends.core;

import org.junit.Test;

public class GenericConfigurableContextTest {
    @Test
    public void should() {
        ConfigurableContext configurableContext = new GenericConfigurableContext();
        configurableContext.configure(
                new GenericConfigurationBuilder()
                    .propertySource(new PropertySource("properties1.properties"))
                    .propertySource(new PropertySource("properties2.properties"))
                    .module("module1")
                    .module("module2", new GenericModuleBuilder())
        );
    }
}
