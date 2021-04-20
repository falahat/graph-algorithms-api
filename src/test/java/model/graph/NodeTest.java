package model.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public abstract class NodeTest extends GraphTest {

    @Test
    public void testGettingZeroNodes() {
        Collection<Object> nodes = graph.nodes();
        Assertions.assertNotNull(nodes, "Must not be null");
        Assertions.assertTrue(nodes.isEmpty(), "Must be empty list");
    }

    @Test
    public void testGettingSingleNodes() {
        Object node1 = testUtil().generateNode(); graph.add(node1);

        Collection<Object> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingNodeByValue() {
        Object node1 = testUtil().generateNode(); graph.add(node1);

        Collection<Object> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
    }

    @Test
    public void testGettingMultipleNodes() {
        Object node1 = testUtil().generateNode(); graph.add(node1);
        Object node2 = testUtil().generateNode(); graph.add(node2);

        Collection<Object> nodes = graph.nodes();
        Assertions.assertTrue(nodes.contains(node1), "Must contain node1");
        Assertions.assertTrue(nodes.contains(node2), "Must contain node2");
    }
}
