package com.karmanno.itdepends.core.graph;

import com.karmanno.itdepends.core.component.*;
import com.karmanno.itdepends.core.exception.DependencyGraphException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DependencyGraphTest {
    @Test
    public void shouldInsertVertex() {
        // when:
        DependencyGraph graph = new DependencyGraph();
        graph.insertVertex("id1", Object.class, null);
        graph.insertVertex("id2", ArrayList.class, null);

        // then:
        var v1 = graph.getVertexes().get("id1");
        var v2 = graph.getVertexes().get("id2");

        assertEquals("id1", v1.getId());
        assertEquals(Object.class, v1.getCls());
        assertEquals("id2", v2.getId());
        assertEquals(ArrayList.class, v2.getCls());
    }

    @Test(expected = DependencyGraphException.class)
    public void shouldFailOnNullId() {
        // when:
        DependencyGraph graph = new DependencyGraph();
        graph.insertVertex(null, Object.class, null);
    }

    @Test
    public void shouldInsertConnection() {
        // when:
        DependencyGraph graph = new DependencyGraph();
        graph.insertVertex("id1", Object.class, null);
        graph.insertVertex("id2", ArrayList.class, null);
        graph.insertConnection("id1", "id2");

        // then:
        var connections = graph.getConnections().get("id1");
        assertNotNull(connections);
        assertEquals(1, connections.size());
        assertEquals("id2", connections.stream().findFirst().orElseThrow());
    }

    @Test(expected = DependencyGraphException.class)
    public void shouldFailOnNullFrom() {
        // when:
        DependencyGraph graph = new DependencyGraph();
        graph.insertVertex("id1", Object.class, null);
        graph.insertVertex("id2", ArrayList.class, null);
        graph.insertConnection(null, "id2");
    }

    @Test(expected = DependencyGraphException.class)
    public void shouldFailOnNullTo() {
        // when:
        DependencyGraph graph = new DependencyGraph();
        graph.insertVertex("id1", Object.class, null);
        graph.insertVertex("id2", ArrayList.class, null);
        graph.insertConnection("id1", null);
    }

    @Test
    public void deepSearchTest() {
        // given:
        DependencyGraph dependencyGraph = new DependencyGraph();
        dependencyGraph.insertVertex("1", BigDecimal.class, new DefaultContextComponentBuilder<>("1", BigDecimal.class).build());
        dependencyGraph.insertVertex("2", BigInteger.class, new DefaultContextComponentBuilder<>("2", BigInteger.class).build());
        dependencyGraph.insertVertex("3", Object.class, new DefaultContextComponentBuilder<>("3", Object.class).build());
        dependencyGraph.insertVertex("4", ArrayList.class, new DefaultContextComponentBuilder<>("4", ArrayList.class).build());
        dependencyGraph.insertVertex("5", HashSet.class, new DefaultContextComponentBuilder<>("5", HashSet.class).build());
        dependencyGraph.insertVertex("6", HashMap.class, new DefaultContextComponentBuilder<>("6", HashMap.class).build());
        dependencyGraph.insertVertex("7", DecimalFormat.class, new DefaultContextComponentBuilder<>("7", DecimalFormat.class).build());
        dependencyGraph.insertVertex("8", DateTimeFormatter.class, new DefaultContextComponentBuilder<>("8", DateTimeFormatter.class).build());
        dependencyGraph.insertVertex("9", LocalDateTime.class, new DefaultContextComponentBuilder<>("9", LocalDateTime.class).build());

        dependencyGraph.insertConnection("1", "2");
        dependencyGraph.insertConnection("1", "3");
        dependencyGraph.insertConnection("3", "4");
        dependencyGraph.insertConnection("3", "5");
        dependencyGraph.insertConnection("4", "6");
        dependencyGraph.insertConnection("6", "7");
        dependencyGraph.insertConnection("6", "8");
        dependencyGraph.insertConnection("8", "9");

        // when:
        var components = dependencyGraph.deepSearch("1");

        // then:
        var seq = components.stream().map(DependencyNode::getId).collect(Collectors.joining(","));
        assertEquals("2,7,9,8,6,4,5,3,1", seq);
    }
}
