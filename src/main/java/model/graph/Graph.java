package model.graph;

import model.node.Node;

public interface Graph extends ReadOnlyGraph {
    void add(Node node);
    void remove(Node node);

    void connect(Node node1, Node node2);
    void disconnect(Node node1, Node node2);
}
