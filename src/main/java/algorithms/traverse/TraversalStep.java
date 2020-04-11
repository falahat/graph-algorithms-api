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

    public static <N, E> StepToNode<N, E> toNode(Node node) {
        return new StepToNode<>(node);
    }

    public static <N, E> TraversalStep fromInitialNode(Node node) {
        return new TraversalStep(node);
    }

    public static class StepToNode<N, E> {
        private final Node node;

        public StepToNode(Node node) {
            this.node = node;
        }

        public TraversalStep withEdge(Edge edgeToNode) {
            return new TraversalStep(this.node, edgeToNode);
        }

        public TraversalStep withoutEdge() {
            return new TraversalStep(this.node);
        }
    }
}
