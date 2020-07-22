package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.Scope;

import java.util.ArrayList;
import java.util.List;

public class DefaultContextComponentBuilder<T> implements ContextComponentBuilder<T> {
    private final Class<T> componentClass;
    private final List<Class<?>> argumentClasses = new ArrayList<>();
    private Scope scope;
    private ContextComponentFactory<T> contextComponentFactory;

    public DefaultContextComponentBuilder(Class<T> componentClass) {
        this.componentClass = componentClass;
    }

    public DefaultContextComponentBuilder<T> arg(Class<?> cls) {
        argumentClasses.add(cls);
        return this;
    }

    public DefaultContextComponentBuilder<T> factory(ContextComponentFactory<T> contextComponentFactory) {
        this.contextComponentFactory = contextComponentFactory;
        return this;
    }

    public DefaultContextComponentBuilder<T> scope(Scope scope) {
        this.scope = scope;
        return this;
    }

    @Override
    public DefaultContextComponent<T> build() {
        return new DefaultContextComponent<>(
                componentClass,
                scope == null ? Scope.SINGLETON : scope,
                contextComponentFactory == null
                        ? new DefaultContextComponentFactory<>(componentClass, argumentClasses.toArray(new Class<?>[0]))
                        : contextComponentFactory
        );
    }
}
