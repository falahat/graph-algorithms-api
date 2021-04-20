package model.graph;

import model.node.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This serves as a labeled view of an existing graph.
 * Edges and Nodes can be labeled with a general data type.
 * See {@link DelegateGraph} for how this view is automatically kept in sync with the original copy.
 * @param <V> - Label Type
 */
public class LabeledGraph<K, V> extends DelegateGraph<K> {
    private final Map<K, V> labelPerNode;

    public LabeledGraph(Graph<K> innerGraph) {
        super(innerGraph);
        this.labelPerNode = new HashMap<>();
    }

    public Optional<V> get(K node) {
        return Optional.ofNullable(labelPerNode.getOrDefault(node, null));
    }

    public void put(K node, V label) {
        labelPerNode.put(node, label);
    }

    @Override
    public void remove(K node) {
        this.labelPerNode.remove(node);
        super.remove(node);
    }
}
