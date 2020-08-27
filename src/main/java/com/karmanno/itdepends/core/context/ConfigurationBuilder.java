package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ContextComponentBuilder;

public interface ConfigurationBuilder {
    ConfigurationBuilder propertySource(String path);
    ConfigurationBuilder component(ContextComponentBuilder<?> componentBuilder);
    ContextConfiguration build();
}
