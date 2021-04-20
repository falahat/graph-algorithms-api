package model.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * This serves as a labeled view of an existing graph.
 * Edges and Nodes can be labeled with a general data type.
 * See {@link DelegateGraph} for how this view is automatically kept in sync with the original copy.
 * @param <V> - Label Type
 */
public class DelegatingLabeledGraph<K, V> extends DelegateGraph<K> implements LabeledGraph<K, V> {
    private final Map<K, V> labelPerNode;

    public DelegatingLabeledGraph(Graph<K> innerGraph) {
        super(innerGraph);
        this.labelPerNode = new HashMap<>();
    }

    @Override
    public V get(K node) {
        return labelPerNode.getOrDefault(node, null);
    }

    @Override
    public void put(K node, V label) {
        labelPerNode.put(node, label);
    }

    @Override
    public void remove(K node) {
        this.labelPerNode.remove(node);
        super.remove(node);
    }
}
