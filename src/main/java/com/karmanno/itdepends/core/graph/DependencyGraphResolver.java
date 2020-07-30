package com.karmanno.itdepends.core.graph;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.exception.DependencyResolveException;

import java.util.List;
import java.util.Map;

public class DependencyGraphResolver {
    private final DependencyGraph dependencyGraph = new DependencyGraph();

    public DependencyGraph build(Map<String, ContextComponent<?>> componentMap) {
        dependencyGraph.clear();
        componentMap.values().forEach(contextComponent ->
                dependencyGraph.insertVertex(contextComponent.id(), contextComponent.componentClass(), contextComponent));

        componentMap.values().forEach(contextComponent -> {
            Class<?>[] dependencies = contextComponent.componentFactory().argumentClasses();
            for (Class<?> dependency : dependencies) {
                List<DependencyNode> candidates = dependencyGraph.search(dependency);
                if (candidates.size() == 0)
                    throw new DependencyResolveException("No candidates for the dependency");

                if (candidates.size() == 1)  { // no alternatives
                    var node = candidates.get(0);
                    dependencyGraph.insertConnection(contextComponent.id(), node.getId());
                }
            }
        });
        return dependencyGraph;
    }
}
