package model.graph;

import model.edge.Edge;
import model.node.Node;

public interface Graph<V extends Cloneable>extends ReadOnlyGraph<V> {
    Node add(V value);

    Edge connect(Node node1, Node node2);

    default Edge connect(V nodeValue1, V nodeValue2) {
        return connect(node(nodeValue1), node(nodeValue2));
    }

    void remove(Node node);

    void disconnect(Node node1, Node node2);

    default void disconnect(V nodeValue1, V nodeValue2) {
        disconnect(node(nodeValue1), node(nodeValue2));
    }
}
