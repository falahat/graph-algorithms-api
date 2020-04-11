package model.graph;


import model.edge.Edge;
import model.node.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public abstract class EdgeDirectedTest<N, E> extends EdgeTest<N, E> {

    @Test
    public void testMultipleOutgoingAndIncoming() {
        int numIncoming = 5;
        int numOutgoing = 3;

        Node centerNode = itemGenerator().generateNode();
        List<Node> incomingNodes = itemGenerator().generateNodes(numIncoming);
        List<Node> outgoingNodes = itemGenerator().generateNodes(numOutgoing);

        incomingNodes.forEach(parentNode -> graph.connect(parentNode, centerNode));
        outgoingNodes.forEach(childNode -> graph.connect(centerNode, childNode));

        Collection<Edge> edges = graph.edges(centerNode);
        Assertions.assertNotNull(edges, "Edges must not be null");
        Assertions.assertEquals(edges.size(), numIncoming + numOutgoing, "Incorrect number of edges");
        for (Edge edge : edges) {
            Node otherNode = edge.other(centerNode);
            if (incomingNodes.contains(otherNode)) {
                Assertions.assertEquals(edge.source(), otherNode);
                Assertions.assertEquals(edge.destination(), centerNode);
            } else if (outgoingNodes.contains(otherNode)) {
                Assertions.assertEquals(edge.source(), centerNode);
                Assertions.assertEquals(edge.destination(), otherNode);
            } else {
                Assertions.fail("Must be recognized node: " + otherNode.toString());
            }
        }

        Collection<Node> neighbors = graph.neighbors(centerNode);
        Assertions.assertNotNull(neighbors, "Neighbors must not be null");
        Assertions.assertEquals(neighbors.size(), numOutgoing, "Incorrect number of neighbors");
        neighbors.forEach(neighbor -> Assertions.assertTrue(outgoingNodes.contains(neighbor), "Must be outbound node"));
    }

    @Test
    public void testIncomingEdgeNotReturned() {
        List<Node> nodes = itemGenerator().generateNodes(2);

        Edge edge1 = graph.connect(nodes.get(0), nodes.get(1));

        Collection<Edge> edges = graph.edges(nodes.get(1));
        Assertions.assertTrue(edges.isEmpty(), "Must not contain edge1");
    }
}
