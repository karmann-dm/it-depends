package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.graph.DependencyGraph;
import com.karmanno.itdepends.core.graph.DependencyGraphResolver;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Stream;

public class GenericConfigurableContext implements ConfigurableContext {
    private ContextConfiguration contextConfiguration = new GenericContextConfiguration();
    private DependencyGraph dependencyGraph;

    @Override
    public ContextConfiguration getConfiguration() {
        return contextConfiguration;
    }

    @Override
    public GenericConfigurableContext configure(ConfigurationBuilder builder) {
        contextConfiguration = builder.build();
        return this;
    }

    @Override
    public Optional<ContextComponent<?>> getComponentById(String id) {
        return Optional.ofNullable(contextConfiguration.getComponents().get(id));
    }

    @Override
    public <T> Optional<ContextComponent<?>> getComponentByClass(Class<T> cls) {
        return contextConfiguration.getComponents()
                .values()
                .stream()
                .filter(contextComponent -> contextComponent.componentClass().equals(cls))
                .findFirst();
    }

    @Override
    public <T> Stream<ContextComponent<T>> getComponentsByClass(Class<T> cls) {
        return null;
    }

    @Override
    public <T> Optional<T> getInstanceByClass(Class<T> cls) {
        return (Optional<T>) contextConfiguration.getInstances()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getContextComponent().componentClass().equals(cls))
                .map(entry -> entry.getValue().getInstance())
                .findFirst();
    }

    @Override
    public void resolve() {
        dependencyGraph = new DependencyGraphResolver().build(contextConfiguration.getComponents());
        dependencyGraph.getVertexes().values()
                .forEach(vertex -> {
                    var resolveSequence = dependencyGraph.deepSearch(vertex.getId());
                    var arguments = new ArrayList<>();
                    resolveSequence.forEach(v -> {
                        var localArgs = new ArrayList<>();
                        if (dependencyGraph.getConnections().get(v.getId()) != null) {
                            dependencyGraph.getConnections().get(v.getId()).forEach(connection -> {
                                localArgs.add(dependencyGraph.getVertexes().get(connection).getComponentInstance().getInstance());
                            });
                        }

                        if (v.getComponentInstance() == null) {
                            v.setComponentInstance(new ComponentInstance<>(v.getContextComponent(), localArgs.toArray(new Object[0])));
                        }
                        arguments.add(v.getComponentInstance().getInstance());
                    });
                });
    }
}
