package model.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public abstract class NodeTest<K> extends GraphTest<K> {

    @Test
    public void testGettingZeroNodes() {
        Collection<K> nodes = graph.nodes();
        Assertions.assertNotNull(nodes, "Must not be null");
        Assertions.assertTrue(nodes.isEmpty(), "Must be empty list");
    }

    @Test
    public void testGettingSingleNodes() {
        K node1 = itemGenerator().generateNode(); graph.add(node1);

        Collection<K> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingNodeByValue() {
        K node1 = itemGenerator().generateNode(); graph.add(node1);

        Collection<K> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingMultipleNodes() {
        K node1 = itemGenerator().generateNode(); graph.add(node1);
        K node2 = itemGenerator().generateNode(); graph.add(node2);

        Collection<K> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
        Assertions.assertTrue(nodes.contains(node2), "Must contain node2");
    }
}
