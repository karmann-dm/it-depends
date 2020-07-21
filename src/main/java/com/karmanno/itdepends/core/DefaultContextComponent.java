package com.karmanno.itdepends.core;

public class DefaultContextComponent implements ContextComponent {
    private final ContextComponentFactory<?> contextComponentFactory;
    private final Class<?> componentClass;

    public DefaultContextComponent(Class<?> componentClass, Object... arguments) {
        this.componentClass = componentClass;
        this.contextComponentFactory = new DefaultContextComponentFactory(componentClass, arguments);
    }

    @Override
    public Class<?> componentClass() {
        return componentClass;
    }

    @Override
    public ContextComponentFactory<?> componentFactory() {
        return contextComponentFactory;
    }
}
