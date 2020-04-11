package algorithms.traverse;

import model.edge.Edge;
import model.node.Node;

import java.util.Optional;

public class TraversalStep {
    private final Node node;
    private final Edge edgeToNode;

    public TraversalStep(Node node, Edge edgeToNode) {
        this.node = node;
        this.edgeToNode = edgeToNode;
    }

    public TraversalStep(Node node) {
        this(node, null);
    }

    public Node node() {
        return node;
    }

    public Optional<Edge> edgeToNode() {
        return Optional.ofNullable(edgeToNode);
    }

    public static TraversalStep fromInitialNode(Node node) {
        return new TraversalStep(node);
    }

}
