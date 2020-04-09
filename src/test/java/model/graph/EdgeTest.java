package model.graph;

import model.edge.Edge;
import model.node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public abstract class EdgeTest<N extends Node, E extends Edge<N>> extends GraphTest<N, E> {

    @Test
    public void testGettingZeroEdges() {
        Collection<E> edges = graph.edges();
        Assert.assertNotNull("Must not be null", edges);
        Assert.assertTrue("Must be empty list", edges.isEmpty());
    }

    @Test
    public void testGettingSingleEdge() {
        List<N> nodes = itemGenerator().generateNodes(2);
        E edge1 = itemGenerator().generateEdge(nodes.get(0), nodes.get(1));

        graph.add(edge1);

        Collection<E> edges = graph.edges();
        Assert.assertTrue("Must contain edge1", edges.contains(edge1));
    }

    @Test
    public void testGettingMultipleEdges() {
        List<N> nodes = itemGenerator().generateNodes(4);
        E edge1 = itemGenerator().generateEdge(nodes.get(0), nodes.get(1));
        E edge2 = itemGenerator().generateEdge(nodes.get(2), nodes.get(3));

        graph.add(edge1);
        graph.add(edge2);

        Collection<E> edges = graph.edges();
        Assert.assertTrue("Must contain edge1", edges.contains(edge1));
        Assert.assertTrue("Must contain edge2", edges.contains(edge2));
    }
}
