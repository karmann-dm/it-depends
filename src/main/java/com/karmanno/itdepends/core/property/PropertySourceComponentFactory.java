package com.karmanno.itdepends.core.property;

import com.karmanno.itdepends.core.component.ContextComponentFactory;

public class PropertySourceComponentFactory implements ContextComponentFactory<PropertySource> {
    @Override
    public Class<?>[] argumentClasses() {
        return new Class[0];
    }

    @Override
    public PropertySource create(Object... arguments) {
        return null;
    }
}
