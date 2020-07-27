package com.karmanno.itdepends.core.component;

public class ComponentInstance<T> {
    private ContextComponent<T> contextComponent;
    private T instance;
    private Object[] args;

    public ComponentInstance(ContextComponent<T> contextComponent, Object... args) {
        this.contextComponent = contextComponent;
        this.args = args;
        if (contextComponent.instantiationPolicy().equals(InstantiationPolicy.INSTANT)) {
            instantiate();
        }
    }

    public void instantiate() {
        this.instance = contextComponent.componentFactory().create(args);
    }

    public T getInstance() {
        return instance;
    }

    public ContextComponent<T> getContextComponent() {
        return contextComponent;
    }
}
