package model.graph;

import model.edge.Edge;
import model.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This serves as a labeled view of an existing graph.
 * Edges and Nodes can be labeled with a general data type.
 * See {@link DelegateGraph} for how this view is automatically kept in sync with the original copy.
 * @param <N>
 * @param <E>
 * @param <L>
 * @param <L>
 */
public class LabeledGraph<N extends Node, E extends Edge<N>, L> extends DelegateGraph<N, E> {
    private final Map<N, L> labelPerNode;
    private final Map<E, L> labelPerEdge;

    public LabeledGraph(Graph<N, E> innerGraph) {
        super(innerGraph);
        this.labelPerNode = new HashMap<>();
        this.labelPerEdge = new HashMap<>();
    }

    public Optional<L> getLabel(N node) {
        return Optional.ofNullable(labelPerNode.getOrDefault(node, null));
    }

    public Optional<L> getLabel(E edge) {
        return Optional.ofNullable(labelPerEdge.getOrDefault(edge, null));
    }

    public void setLabel(N node, L label) {
        labelPerNode.put(node, label);
    }

    public void setLabel(E edge, L label) {
        labelPerEdge.put(edge, label);
    }

    @Override
    public void remove(N node) {
        super.remove(node);
        this.labelPerNode.remove(node);
    }

    @Override
    public void remove(E edge) {
        super.remove(edge);
        this.labelPerEdge.remove(edge);
    }
}
