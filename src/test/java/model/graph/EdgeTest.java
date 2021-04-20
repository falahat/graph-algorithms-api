package model.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public abstract class EdgeTest extends GraphTest {

    @Test
    public void testSingleConnection() {
        List<Object> nodes = testUtil().generateNodes(2);
        nodes.forEach(this.graph::add);

        graph.connect(nodes.get(0), nodes.get(1));

        assertConnected(nodes.get(0), nodes.get(1));
    }

    @Test
    public void testGettingMultipleEdges() {
        List<Object> nodes = testUtil().generateNodes(4);
        nodes.forEach(graph::add);

        graph.connect(nodes.get(0), nodes.get(1));
        graph.connect(nodes.get(2), nodes.get(3));

        assertConnected(nodes.get(0), nodes.get(1));
        assertConnected(nodes.get(2), nodes.get(3));
    }

    @Test
    public void testDisconnected() {
        List<Object> nodes = testUtil().generateNodes(2);
        nodes.forEach(graph::add);

        graph.connect(nodes.get(0), nodes.get(1));

        assertConnected(nodes.get(0), nodes.get(1));

        graph.disconnect(nodes.get(0), nodes.get(1));

        assertNotConnected(nodes.get(0), nodes.get(1));
    }

    public void assertConnected(Object source, Object destination) {
        Collection<Object> destinations = graph.edges(source);
        Assertions.assertTrue(destinations.contains(destination), "Must contain destination");
        Assertions.assertTrue(graph.isConnected(source, destination));
    }

    public void assertNotConnected(Object source, Object destination) {
        Collection<Object> destinations = graph.edges(source);
        Assertions.assertFalse(destinations.contains(destination), "Must not contain destination");
        Assertions.assertFalse(graph.isConnected(source, destination));
    }
}