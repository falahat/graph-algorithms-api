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
 * @param <L>
 */
public class LabeledGraph<N, L> extends DelegateGraph<N> {
    private final Map<Node, L> labelPerNode;
    private final Map<Edge, L> labelPerEdge;

    public LabeledGraph(Graph<N> innerGraph) {
        super(innerGraph);
        this.labelPerNode = new HashMap<>();
        this.labelPerEdge = new HashMap<>();
    }

    public Optional<L> getLabel(Node node) {
        return Optional.ofNullable(labelPerNode.getOrDefault(node, null));
    }

    public Optional<L> getLabel(Edge edge) {
        return Optional.ofNullable(labelPerEdge.getOrDefault(edge, null));
    }

    public void setLabel(Node node, L label) {
        labelPerNode.put(node, label);
    }

    public void setLabel(Edge edge, L label) {
        labelPerEdge.put(edge, label);
    }

    @Override
    public void remove(Node node) {
        this.labelPerNode.remove(node);
        super.remove(node);
    }

    @Override
    public void disconnect(Node node1, Node node2) {
        Edge toRemove = edge(node1, node2);
        this.labelPerEdge.remove(toRemove);
        super.disconnect(node1, node2);
    }
}
