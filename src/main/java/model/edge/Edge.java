package model.edge;

import model.node.Node;

/**
 * Directed Edge
 */
public class Edge {
    private final Node sourceNode;
    private final Node destinationNode;

    public Edge(Node sourceNode, Node destinationNode) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
    }

    public Node source() {
        return this.sourceNode;
    }

    public Node destination() {
        return this.destinationNode;
    }

    public Node other(Node node) {
        if (node.equals(source())) {
            return destination();
        } else if (node.equals(destination())) {
            return source();
        } else {
            throw new IllegalArgumentException("Node does not exist on this edge");
        }
    }
}
