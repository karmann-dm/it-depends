package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.Scope;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.ContextComponentFactory;

public class DefaultContextComponent<T> implements ContextComponent<T> {
    private final ContextComponentFactory<T> contextComponentFactory;
    private final Class<T> componentClass;
    private final Scope scope;

    DefaultContextComponent(Class<T> componentClass,
                            Scope scope,
                            ContextComponentFactory<T> factory) {
        this.componentClass = componentClass;
        this.scope = scope;
        this.contextComponentFactory = factory;
    }

    @Override
    public Class<T> componentClass() {
        return componentClass;
    }

    @Override
    public ContextComponentFactory<T> componentFactory() {
        return contextComponentFactory;
    }

    @Override
    public Scope scope() {
        return scope;
    }
}
