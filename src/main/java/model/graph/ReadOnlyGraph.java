package model.graph;

import model.edge.Edge;
import model.node.Node;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @param <V> - The type of values stored for each node of this graph.
 */
public interface ReadOnlyGraph<V extends Cloneable> {
    List<Node> nodes();

    List<Node> nodes(V targetValue);

    default Node node(V targetValue) {
        return nodes(targetValue).get(0);
    }

    V value(Node node);

    Edge edge(Node node1, Node node2);

    List<Edge> edges();

    List<Edge> edges(Node node);

    boolean contains(Node node);

    default List<Node> neighbors(Node node) {
        return edges(node)
                .stream()
                .filter(edge -> node.equals(edge.source()))
                .map(edge -> edge.other(node))
                .collect(Collectors.toList());
    }
}
