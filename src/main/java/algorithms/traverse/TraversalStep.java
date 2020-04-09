package algorithms.traverse;

import model.edge.Edge;
import model.node.Node;

import java.util.Optional;

public class TraversalStep<N extends Node, E extends Edge<N>> {
    private final N node;
    private final E edgeToNode;

    public TraversalStep(N node, E edgeToNode) {
        this.node = node;
        this.edgeToNode = edgeToNode;
    }

    public TraversalStep(N node) {
        this(node, null);
    }

    public N node() {
        return node;
    }

    public Optional<E> edgeToNode() {
        return Optional.ofNullable(edgeToNode);
    }

    public static <N extends Node, E extends Edge<N>> StepToNode<N, E> toNode(N node) {
        return new StepToNode<>(node);
    }

    public static <N1 extends Node, E1 extends Edge<N1>> TraversalStep<N1, E1> fromInitialNode(N1 node) {
        return new TraversalStep<N1, E1>(node);
    }

    public static class StepToNode<N extends Node, E extends Edge<N>> {
        private final N node;

        public StepToNode(N node) {
            this.node = node;
        }

        public TraversalStep<N, E> withEdge(E edgeToNode) {
            return new TraversalStep<>(this.node, edgeToNode);
        }

        public TraversalStep<N, E> withoutEdge() {
            return new TraversalStep<>(this.node);
        }
    }
}
