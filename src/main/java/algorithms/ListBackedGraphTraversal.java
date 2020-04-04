package algorithms;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ListBackedGraphTraversal<N extends Node, E extends Edge<N>> extends BaseGraphTraversal<N, E> {
    private List<TraversalStep<N, E>> possibleSteps;

    public ListBackedGraphTraversal(Graph<N, E> graph) {
        super(graph);
        this.possibleSteps = new ArrayList<>();
    }

    @Override
    public void addPossibleTraversals(Collection<TraversalStep<N, E>> nextPossible) {
        possibleSteps.addAll(nextPossible); // Goes to end of list
    }

    @Override
    public Optional<TraversalStep<N, E>> selectAndRemoveNextCandidate() {
        if (possibleSteps.isEmpty()) {
            return Optional.empty();
        }
        // DFS and BFS can override this "selectNextStep" to either return the 1st element (bfs) or last (dfs)
        int indexOfResult = selectNextStep(possibleSteps);
        TraversalStep<N, E> nextStep = possibleSteps.remove(indexOfResult);
        return Optional.of(nextStep);
    }

    abstract int selectNextStep(List<TraversalStep<N, E>> nextSteps);
}
