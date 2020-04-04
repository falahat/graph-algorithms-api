package algorithms;

import model.Edge;
import model.Graph;
import model.Node;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseGraphTraversal<N extends Node, E extends Edge<N>> implements GraphTraversal<N, E> {
    Graph<N, E> graph;
    private Set<N> visitedNodes;

    private TraversalStep<N, E> currentStep;

    public BaseGraphTraversal(Graph<N, E> graph) {
        this.graph = graph;
        this.visitedNodes = new HashSet<>();
        addPossibleTraversals(getTraversalsFromInitialNodes());
    }

    @Override
    public final boolean hasNext() {

        while (this.currentStep == null) {
            Optional<TraversalStep<N, E>> nextPossibleStepOpt = selectAndRemoveNextCandidate();
            if (nextPossibleStepOpt.isEmpty()) {
                break;
            }

            TraversalStep<N, E> nextPossibleStep = nextPossibleStepOpt.get();
            if (isNotVisited(nextPossibleStep) && canVisit(nextPossibleStep)) {
                this.currentStep = nextPossibleStep;
                // This will be visited when we return the next node in #next()
            }
        }

        return this.currentStep != null;
    }

    @Override
    public final TraversalStep<N, E> next() {
        if (hasNext()) {
            TraversalStep<N, E> toReturn = this.currentStep;

            // Mark this node as visted and call hooks
            markAsVisited(this.currentStep);
            onVisit(this.currentStep);

            // Calculate + insert next possible steps to traverse
            N currentNode = currentStep.node();
            List<TraversalStep<N, E>> nextSteps = graph.edges(currentNode)
                    .stream()
                    .map(edgeToNextNode -> new TraversalStep<>(edgeToNextNode.other(currentNode), edgeToNextNode))
                    .collect(Collectors.toList());

            addPossibleTraversals(nextSteps);

            return toReturn;
        }

        throw new NoSuchElementException("Graph is fully traversed");
    }

    public List<N> selectInitialNodes() {
        return new ArrayList<>(graph.nodes());
    }

    @Override
    public void markAsVisited(TraversalStep<N, E> step) {
        this.visitedNodes.add(step.node());
    }

    @Override
    public boolean isNotVisited(TraversalStep<N, E> step) {
        return !visitedNodes.contains(step.node());
    }

    public abstract void addPossibleTraversals(Collection<TraversalStep<N, E>> nextPossible);

    public abstract Optional<TraversalStep<N, E>> selectAndRemoveNextCandidate(); // TODO: rename to popNextCandidate?

    private List<TraversalStep<N, E>> getTraversalsFromInitialNodes() {
        return selectInitialNodes().stream()
                .map(TraversalStep::<N, E>fromInitialNode)
                .collect(Collectors.toList());
    }
}
