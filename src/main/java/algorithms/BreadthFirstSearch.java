package algorithms;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.List;

public class BreadthFirstSearch<N extends Node, E extends Edge<N>>  extends ListBackedGraphTraversal<N, E> {
    public BreadthFirstSearch(Graph<N, E> graph) {
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
}
