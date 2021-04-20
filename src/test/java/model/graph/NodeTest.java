package model.graph;

import model.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Collection;

public abstract class NodeTest<N extends Node> extends GraphTest<N> {

    @Test
    public void testGettingZeroNodes() {
        Collection<Node> nodes = graph.nodes();
        Assertions.assertNotNull(nodes, "Must not be null");
        Assertions.assertTrue(nodes.isEmpty(), "Must be empty list");
    }

    @Test
    public void testGettingSingleNodes() {
        Node node1 = itemGenerator().generateNode(); graph.add(node1);

        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingNodeByValue() {
        Node node1 = itemGenerator().generateNode(); graph.add(node1);

        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingMultipleNodes() {
        Node node1 = itemGenerator().generateNode(); graph.add(node1);
        Node node2 = itemGenerator().generateNode(); graph.add(node2);

        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
        Assertions.assertTrue(nodes.contains(node2), "Must contain node2");
    }
}
