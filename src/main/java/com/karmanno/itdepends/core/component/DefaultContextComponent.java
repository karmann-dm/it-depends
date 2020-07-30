package com.karmanno.itdepends.core.component;

public class DefaultContextComponent<T> implements ContextComponent<T> {
    private final String id;
    private final ContextComponentFactory<T> contextComponentFactory;
    private final Class<T> componentClass;
    private final Scope scope;
    private final InstantiationPolicy instantiationPolicy;

    DefaultContextComponent(String id,
                            Class<T> componentClass,
                            Scope scope,
                            ContextComponentFactory<T> factory,
                            InstantiationPolicy instantiationPolicy) {
        assert id != null;
        assert componentClass != null;
        assert scope != null;
        assert factory != null;
        assert instantiationPolicy != null;

        this.id = id;
        this.componentClass = componentClass;
        this.scope = scope;
        this.contextComponentFactory = factory;
        this.instantiationPolicy = instantiationPolicy;
    }

    @Override
    public String id() {
        return id;
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

    @Override
    public InstantiationPolicy instantiationPolicy() {
        return instantiationPolicy;
    }
}
