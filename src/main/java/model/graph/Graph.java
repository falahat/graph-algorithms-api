package model.graph;

import model.edge.Edge;
import model.node.Node;

public interface Graph<N> extends ReadOnlyGraph<N> {
    Node add(N node);

    Edge connect(Node node1, Node node2);

    default Edge connect(N nodeValue1, N nodeValue2) {
        return connect(node(nodeValue1), node(nodeValue2));
    }

    void remove(Node node);

    void disconnect(Node node1, Node node2);

    default void disconnect(N nodeValue1, N nodeValue2) {
        disconnect(node(nodeValue1), node(nodeValue2));
    }
}
