package algorithms.traverse;

import model.node.Node;

import java.util.Iterator;

public interface GraphTraversal<K> extends Iterator<K> {
    void markVisited(K step);
    boolean isVisited(K step);

    default boolean canVisit(K node) {
        return !isVisited(node);
    }

    default void onVisit(K destination) {} // Do Nothing

    GraphTraversal<K> copy();
}
