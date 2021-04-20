package algorithms.traverse;

import model.node.Node;

import java.util.Iterator;

public interface GraphTraversal extends Iterator<Node>, Iterable<Node> {
    void markAsVisited(Node step);
    boolean isNotVisited(Node step);

    default boolean canVisit(Node node) {
        return isNotVisited(node);
    }

    default void onVisit(Node destination) {} // Do Nothing

    @Override
    default Iterator<Node> iterator() {
        return copy();
    }

    GraphTraversal copy();
}
