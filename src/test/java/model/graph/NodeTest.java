package model.graph;

import model.edge.Edge;
import model.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Collection;

public abstract class NodeTest<N extends Node, E extends Edge> extends GraphTest<N, E> {

    @Test
    public void testGettingZeroNodes() {
        Collection<Node> nodes = graph.nodes();
        Assertions.assertNotNull(nodes, "Must not be null");
        Assertions.assertTrue(nodes.isEmpty(), "Must be empty list");
    }

    @Test
    public void testGettingSingleNodes() {
        Node node1 = graph.add(itemGenerator().generateNodeKey());

        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingNodeByValue() {
        Node node1 = graph.add(itemGenerator().generateNodeKey());

        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingMultipleNodes() {
        Node node1 = graph.add(itemGenerator().generateNodeKey());
        Node node2 = graph.add(itemGenerator().generateNodeKey());


        Collection<Node> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
        Assertions.assertTrue(nodes.contains(node2), "Must contain node2");
    }
}
