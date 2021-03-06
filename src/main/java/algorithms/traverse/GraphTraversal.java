package algorithms.traverse;

import java.util.Iterator;

public interface GraphTraversal<K> extends Iterator<K>, Iterable<K> {
    void markVisited(K step);
    boolean isVisited(K step);

    default boolean canVisit(K node) {
        return !isVisited(node);
    }

    default void onVisit(K destination) {} // Do Nothing

    default Iterator<K> iterator() {
        return this;
    }
}
