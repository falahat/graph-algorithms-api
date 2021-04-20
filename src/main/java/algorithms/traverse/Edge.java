package algorithms.traverse;

import model.node.Node;

import java.util.Optional;

public class Edge {
    private final Node destination;
    private final Node source;

    public Edge(Node destination, Node source) {
        this.destination = destination;
        this.source = source;
    }

    public Edge(Node destination) {
        this(destination, null);
    }

    public Node destination() {
        return destination;
    }

    public Node source() {
        return source;
    }
//
//    public static Edge fromInitialNode(Node node) {
//        return new Edge(node);
//    }

}
