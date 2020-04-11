package model.graph;

import model.edge.Edge;
import model.node.Node;

public interface Graph extends ReadOnlyGraph {
    Node add(String node);

    Edge connect(Node node1, Node node2);

    default Edge connect(String nodeValue1, String nodeValue2) {
        return connect(node(nodeValue1), node(nodeValue2));
    }

    void remove(Node node);

    void disconnect(Node node1, Node node2);

    default void disconnect(String nodeValue1, String nodeValue2) {
        disconnect(node(nodeValue1), node(nodeValue2));
    }
}
