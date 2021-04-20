package model.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public abstract class EdgeTest<N, GRAPH extends Graph<N>> extends GraphTest<N, GRAPH> {

    @Test
    public void testSingleConnection() {
        List<N> nodes = itemGenerator().generateNodes(2);
        nodes.forEach(graph::add);

        connect(nodes.get(0), nodes.get(1));

        assertConnected(nodes.get(0), nodes.get(1));
    }

    @Test
    public void testGettingMultipleEdges() {
        List<N> nodes = itemGenerator().generateNodes(4);
        nodes.forEach(graph::add);

        connect(nodes.get(0), nodes.get(1));
        connect(nodes.get(2), nodes.get(3));

        assertConnected(nodes.get(0), nodes.get(1));
        assertConnected(nodes.get(2), nodes.get(3));
    }

    @Test
    public void testDisconnected() {
        List<N> nodes = itemGenerator().generateNodes(2);
        nodes.forEach(graph::add);

        connect(nodes.get(0), nodes.get(1));

        assertConnected(nodes.get(0), nodes.get(1));

        disconnect(nodes.get(0), nodes.get(1));

        assertNotConnected(nodes.get(0), nodes.get(1));
    }

    public void connect(N source, N destination) {
        graph.connect(source, destination);
    }

    public void disconnect(N source, N destination) {
        graph.disconnect(source, destination);
    }

    public void assertConnected(N source, N destination) {
        Collection<N> destinations = graph.edges(source);
        Assertions.assertTrue(destinations.contains(destination), "Must contain destination");
        Assertions.assertTrue(graph.isConnected(source, destination));
    }

    public void assertNotConnected(N source, N destination) {
        Collection<N> destinations = graph.edges(source);
        Assertions.assertFalse(destinations.contains(destination), "Must not contain destination");
        Assertions.assertFalse(graph.isConnected(source, destination));
    }
}