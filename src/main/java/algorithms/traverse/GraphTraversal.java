package algorithms.traverse;

import java.util.Iterator;

public interface GraphTraversal <N, E> extends Iterator<TraversalStep>, Iterable<TraversalStep> {
    void markAsVisited(TraversalStep step);
    boolean isNotVisited(TraversalStep step);

    default boolean canVisit(TraversalStep step) {
        return isNotVisited(step);
    }

    default void onVisit(TraversalStep step) {} // Do Nothing

    @Override
    default Iterator<TraversalStep> iterator() {
        return copy();
    }

    GraphTraversal<N, E> copy();
}
