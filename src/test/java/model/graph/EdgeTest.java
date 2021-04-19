package model.graph;

import model.edge.Edge;
import model.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public abstract class EdgeTest<N, E> extends GraphTest<N> {

    @Test
    public void testGettingZeroEdges() {
        Collection<Edge> edges = graph.edges();
        Assertions.assertNotNull(edges, "Must not be null");
        Assertions.assertTrue(edges.isEmpty(), "Must be empty list");
    }

    @Test
    public void testGettingSingleEdge() {
        List<Node> nodes = itemGenerator().generateNodes(2);

        Edge edge1 = graph.connect(nodes.get(0), nodes.get(1));

        Collection<Edge> edges = graph.edges();
        Assertions.assertTrue(edges.contains(edge1), "Must contain edge1");
    }

    @Test
    public void testGettingMultipleEdges() {
        List<Node> nodes = itemGenerator().generateNodes(4);
        Edge edge1 = graph.connect(nodes.get(0), nodes.get(1));
        Edge edge2 = graph.connect(nodes.get(2), nodes.get(3));

        Collection<Edge> edges = graph.edges();
        Assertions.assertTrue(edges.contains(edge1), "Must contain edge1");
        Assertions.assertTrue(edges.contains(edge2), "Must contain edge2");
    }
}
