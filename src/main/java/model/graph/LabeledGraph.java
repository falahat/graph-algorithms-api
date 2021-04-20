package model.graph;

import algorithms.traverse.Edge;
import model.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * This serves as a labeled view of an existing graph.
 * Edges and Nodes can be labeled with a general data type.
 * See {@link DelegateGraph} for how this view is automatically kept in sync with the original copy.
 * @param <L> - Label Type
 */
public class LabeledGraph<L extends Node> extends DelegateGraph {
    private final Map<Node, L> labelPerNode;

    public LabeledGraph(Graph innerGraph) {
        super(innerGraph);
        this.labelPerNode = new HashMap<>();
    }

    public Optional<L> getLabel(Node node) {
        return Optional.ofNullable(labelPerNode.getOrDefault(node, null));
    }

    public void setLabel(Node node, L label) {
        labelPerNode.put(node, label);
    }

    @Override
    public void remove(Node node) {
        this.labelPerNode.remove(node);
        super.remove(node);
    }
}
