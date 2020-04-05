package algorithms;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.Iterator;
import java.util.List;

public class BreadthFirstTraversal<N extends Node, E extends Edge<N>>  extends ListBackedGraphTraversal<N, E> {
    protected BreadthFirstTraversal(Graph<N, E> graph) {
        super(graph);
    }

    @Override
    int selectNextStep(List<TraversalStep<N, E>> nextSteps) {
        // For Breadth First Search, we want to explore the nodes which were first added to the queue, like a queue.
        // New nodes to visit are added to the end of "nextSteps", so we will instead select the elements at the
        // begining of the list (i.e. at the front of the line.)
        // This performs as a Stack and has First-In-First-Out (LIFO) behavior
        return nextSteps.size() - 1;
    }

    public static class BreadFirstTraversalIterable<N extends Node, E extends Edge<N>> implements Iterable<TraversalStep<N, E>> {
        private final Graph<N, E> graph;

        public BreadFirstTraversalIterable(Graph<N, E> graph) {
            this.graph = graph;
        }

        @Override
        public Iterator<TraversalStep<N, E>> iterator() {
            return new BreadthFirstTraversal<>(this.graph);
        }
    }
}
