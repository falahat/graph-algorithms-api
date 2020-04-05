package algorithms.traverse;

import model.Edge;
import model.Node;

import java.util.Iterator;

public interface GraphTraversal <N extends Node, E extends Edge<N>> extends Iterator<TraversalStep<N, E>>, Iterable<TraversalStep<N, E>> {
    void markAsVisited(TraversalStep<N, E> step);
    boolean isNotVisited(TraversalStep<N, E> step);

    default boolean canVisit(TraversalStep<N, E> step) {
        return isNotVisited(step);
    }

    default void onVisit(TraversalStep<N, E> step) {} // Do Nothing

    @Override
    default Iterator<TraversalStep<N, E>> iterator() {
        return copy();
    }

    GraphTraversal<N, E> copy();
}
