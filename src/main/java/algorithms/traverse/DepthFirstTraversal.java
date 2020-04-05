package algorithms.traverse;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.List;

public class DepthFirstTraversal<N extends Node, E extends Edge<N>>  extends ListBackedGraphTraversal<N, E> {
    public DepthFirstTraversal(Graph<N, E> graph) {
        super(graph);
    }

    @Override
    int selectNextStep(List<TraversalStep<N, E>> nextSteps) {
        // For Depth First Search, we want to explore the nodes which were most recently added to the queue, like a stack.
        // New nodes to visit are added to the end of "nextSteps", which is why we select the last element here.
        // This performs as a Stack and has Last-In-First-Out (LIFO) behavior
        return nextSteps.size() - 1;
    }
}
