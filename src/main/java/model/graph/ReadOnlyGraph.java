package model.graph;

import model.edge.Edge;
import model.node.Node;

import java.util.List;
import java.util.stream.Collectors;

public interface ReadOnlyGraph<N, E> {
    List<Node> nodes();

    List<Node> nodes(N targetValue);

    default Node node(N targetValue) {
        return nodes(targetValue).get(0);
    }

    Edge edge(Node node1, Node node2);

    default Edge edge(E targetValue) {
        return edges(targetValue).get(0);
    }

    List<Edge> edges();

    List<Edge> edges(E targetValue);

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
