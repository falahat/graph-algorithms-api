package model.graph;

import junit.framework.AssertionFailedError;
import model.edge.DirectedEdge;
import model.node.Node;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public abstract class EdgeDirectedTest<N extends Node, E extends DirectedEdge<N>> extends GraphTest<N, E> {

    @Test
    public void testGettingSingleEdge() {
        List<N> nodes = itemGenerator().generateNodes(2);
        E edge1 = itemGenerator().generateEdge(nodes.get(0), nodes.get(1));

        graph.add(edge1);

        Collection<E> edges = graph.edges(nodes.get(0));
        Assert.assertTrue("Must contain edge1", edges.contains(edge1));
    }

    @Test
    public void testMultipleOutgoingAndIncoming() {
        int numIncoming = 5;
        int numOutgoing = 3;

        N centerNode = itemGenerator().generateNode();
        List<N> incomingNodes = itemGenerator().generateNodes(numIncoming);
        List<N> outgoingNodes = itemGenerator().generateNodes(numOutgoing);

        incomingNodes.forEach(parentNode -> graph.add(itemGenerator().generateEdge(parentNode, centerNode)));
        outgoingNodes.forEach(childNode -> graph.add(itemGenerator().generateEdge(centerNode, childNode)));

        Collection<E> edges = graph.edges(centerNode);
        Assert.assertNotNull("Edges must not be null", edges);
        Assert.assertEquals("Incorrect number of edges", edges.size(), numIncoming + numOutgoing);
        for (DirectedEdge<N> edge : edges) {
            N otherNode = edge.other(centerNode);
            if (incomingNodes.contains(otherNode)) {
                Assert.assertEquals(edge.node1(), otherNode);
                Assert.assertEquals(edge.node2(), centerNode);
            } else if (outgoingNodes.contains(otherNode)) {
                Assert.assertEquals(edge.node1(), centerNode);
                Assert.assertEquals(edge.node2(), otherNode);
            } else {
                throw new AssertionFailedError("Must be recognized node: " + otherNode.toString());
            }
            Assert.assertTrue(edge.isDirected());
        }

        Collection<N> neighbors = graph.neighbors(centerNode);
        Assert.assertNotNull("Neighbors must not be null", neighbors);
        Assert.assertEquals("Incorrect number of neighbors", neighbors.size(), numOutgoing);
        neighbors.forEach(neighbor -> Assert.assertTrue("Must be outbound node", outgoingNodes.contains(neighbor)));
    }

    @Test
    public void testIncomingEdgeNotReturned() {
        List<N> nodes = itemGenerator().generateNodes(2);
        E edge1 = itemGenerator().generateEdge(nodes.get(0), nodes.get(1));

        graph.add(edge1);

        Collection<E> edges = graph.edges(nodes.get(1));
        Assert.assertTrue("Must not contain edge1", edges.isEmpty());
    }
}
