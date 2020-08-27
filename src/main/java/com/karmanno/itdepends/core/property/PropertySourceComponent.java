package com.karmanno.itdepends.core.property;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.ContextComponentFactory;
import com.karmanno.itdepends.core.component.InstantiationPolicy;
import com.karmanno.itdepends.core.component.Scope;

import java.util.UUID;

public class PropertySourceComponent implements ContextComponent<PropertySource> {
    @Override
    public String id() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Class<PropertySource> componentClass() {
        return PropertySource.class;
    }

    @Override
    public ContextComponentFactory<PropertySource> componentFactory() {
        return null;
    }

    @Override
    public Scope scope() {
        return Scope.SINGLETON;
    }

    @Override
    public InstantiationPolicy instantiationPolicy() {
        return InstantiationPolicy.INSTANT;
    }
}
