package com.karmanno.itdepends.core.graph;

import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DependencyGraphResolver {
    private DependencyGraph dependencyGraph;

    public DependencyGraph build(Map<String, ContextComponent<?>> componentMap) {
        dependencyGraph.clear();
        componentMap.forEach(((s, contextComponent) -> {
            List<String> links = Arrays.stream(contextComponent.componentFactory().argumentClasses())
                    .map(Class::getCanonicalName)
                    .collect(Collectors.toList());
            dependencyGraph.insert(s, contextComponent.componentClass(), links);
        }));
        return dependencyGraph;
    }
}
