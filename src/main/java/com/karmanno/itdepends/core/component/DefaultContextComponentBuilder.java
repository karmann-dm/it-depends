package com.karmanno.itdepends.core.component;

import java.util.ArrayList;
import java.util.List;

public class DefaultContextComponentBuilder<T> implements ContextComponentBuilder<T> {
    private final String id;
    private final Class<T> componentClass;
    private final List<Class<?>> argumentClasses = new ArrayList<>();
    private Scope scope;
    private ContextComponentFactory<T> contextComponentFactory;
    private InstantiationPolicy instantiationPolicy;

    public DefaultContextComponentBuilder(String id, Class<T> componentClass) {
        this.id = id;
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

    public DefaultContextComponentBuilder<T> instantiationPolicy(InstantiationPolicy instantiationPolicy) {
        this.instantiationPolicy = instantiationPolicy;
        return this;
    }

    @Override
    public DefaultContextComponent<T> build() {
        return new DefaultContextComponent<>(
                id,
                componentClass,
                scope == null ? Scope.SINGLETON : scope,
                contextComponentFactory == null
                        ? new DefaultContextComponentFactory<>(componentClass, argumentClasses.toArray(new Class<?>[0]))
                        : contextComponentFactory,
                instantiationPolicy == null ? InstantiationPolicy.INSTANT : instantiationPolicy
        );
    }
}
