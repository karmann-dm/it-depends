package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ContextComponentBuilder;
import com.karmanno.itdepends.core.property.PropertySource;

public interface ConfigurationBuilder {
    ConfigurationBuilder propertySource(PropertySource propertySource);
    ConfigurationBuilder component(ContextComponentBuilder<?> componentBuilder);
    ContextConfiguration build();
}
