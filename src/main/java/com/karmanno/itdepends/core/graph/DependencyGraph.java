package com.karmanno.itdepends.core.graph;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.exception.DependencyGraphException;

import java.util.*;
import java.util.stream.Collectors;

public class DependencyGraph {
    Map<String, DependencyNode> vertexes = new HashMap<>();
    Map<String, Set<String>> connections = new HashMap<>();

    public void insertVertex(String id, Class<?> cls, ContextComponent<?> contextComponent) {
        if (id == null)
            throw new DependencyGraphException("Id of the vertex can't be null");
        DependencyNode node = new DependencyNode(id, cls, contextComponent);
        vertexes.put(node.getId(), node);
    }

    public void insertConnection(String from, String to) {
        if (from == null)
            throw new DependencyGraphException("From can't be null");
        if (to == null)
            throw new DependencyGraphException("To can't be null");

        if (!connections.containsKey(from))
            connections.put(from, new HashSet<>());
        connections.get(from).add(to);
    }

    public List<DependencyNode> search(Class<?> cls) {
        return vertexes.values().stream()
                .filter(v -> cls.isAssignableFrom(cls))
                .collect(Collectors.toList());
    }

    public Map<String, DependencyNode> getVertexes() {
        return vertexes;
    }

    public Map<String, Set<String>> getConnections() {
        return connections;
    }

    public void clear() {
        vertexes.clear();
        connections.clear();
    }

    public List<DependencyNode> deepSearch(String id) {
        List<DependencyNode> nodes = new ArrayList<>();
        deepSearch(id, nodes);
        return nodes;
    }

    private void deepSearch(String nodeId, List<DependencyNode> nodes) {
        if (!vertexes.get(nodeId).isVisited()) {
            if (connections.get(nodeId) != null) {
                connections.get(nodeId).forEach(connection -> deepSearch(connection, nodes));
            }
            nodes.add(vertexes.get(nodeId));
        }
    }
}
