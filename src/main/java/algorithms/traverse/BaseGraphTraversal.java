package algorithms.traverse;

import model.edge.Edge;
import model.graph.Graph;
import model.node.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseGraphTraversal<N extends Node, E extends Edge<N>> implements GraphTraversal<N, E> {
    private Graph<N, E> graph;
    private Set<N> visitedNodes;
    private boolean isInitialized;
    private List<N> initialNodes;

    private TraversalStep<N, E> currentStep;

    // TODO: investigate "Possible heap pollution from parameterized vararg type"
    public BaseGraphTraversal(Graph<N, E> graph, N... initialNodes) {
        this(graph, Arrays.asList(initialNodes));
    }

    public BaseGraphTraversal(Graph<N, E> graph, List<N> initialNodes) {
        this.graph = graph;
        this.visitedNodes = new HashSet<>();
        this.isInitialized = false;
        this.initialNodes = initialNodes;
    }

    public void initialize() {
        addPossibleTraversals(getTraversalsFromInitialNodes());
    }

    @Override
    public final boolean hasNext() {
        // If we are beginning the search, do a one-time setup of some parameters.
        // We do not want to run this initialization unless all subclasses have also finished their constructors,
        // so we do not call initialize() in BaseGraphTraversal's contructor.
        if (!isInitialized) {
            initialize();
        }

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
        return initialNodes.stream()
                .map(TraversalStep::<N, E>fromInitialNode)
                .collect(Collectors.toList());
    }

    @Override
    public GraphTraversal<N, E> copy() {
        try {
            return (GraphTraversal<N, E>) this.getClass().getConstructors()[0].newInstance(this.graph);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            // TODO: do something
            return null;
        }
    }
}
