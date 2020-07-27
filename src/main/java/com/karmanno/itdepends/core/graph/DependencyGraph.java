package com.karmanno.itdepends.core.graph;

import java.util.*;

public class DependencyGraph {
    List<DependencyNode> vertexes;
    Map<String, Set<String>> connections;

    public void insert(String id, Class<?> cls, List<String> links) {
        DependencyNode node = new DependencyNode(id, cls);

        vertexes.add(node);
        if (!connections.containsKey(node.getId()))
            connections.put(node.getId(), new HashSet<>());
        connections.get(node.getId()).addAll(links);
    }

    public void clear() {
        vertexes.clear();
        connections.clear();
    }
}
