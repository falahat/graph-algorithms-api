package algorithms.traverse;

import model.graph.Graph;
import model.node.Node;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseGraphTraversal<N, E> implements GraphTraversal {
    private Graph graph;
    private Set<Node> visitedNodes;
    private boolean isInitialized;
    private List<Node> initialNodes;

    private TraversalStep currentStep;

    // TODO: investigate "Possible heap pollution from parameterized vararg type"
    public BaseGraphTraversal(Graph graph, Node... initialNodes) {
        this(graph, Arrays.asList(initialNodes));
    }

    public BaseGraphTraversal(Graph graph, List<Node> initialNodes) {
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
            Optional<TraversalStep> nextPossibleStepOpt = selectAndRemoveNextCandidate();
            if (nextPossibleStepOpt.isEmpty()) {
                break;
            }

            TraversalStep nextPossibleStep = nextPossibleStepOpt.get();
            if (isNotVisited(nextPossibleStep) && canVisit(nextPossibleStep)) {
                this.currentStep = nextPossibleStep;
                // This will be visited when we return the next node in #next()
            }
        }

        return this.currentStep != null;
    }

    @Override
    public final TraversalStep next() {
        if (hasNext()) {
            TraversalStep toReturn = this.currentStep;

            // Mark this node as visted and call hooks
            markAsVisited(this.currentStep);
            onVisit(this.currentStep);

            // Calculate + insert next possible steps to traverse
            Node currentNode = currentStep.node();
            List<TraversalStep> nextSteps = graph.edges(currentNode)
                    .stream()
                    .map(edgeToNextNode -> new TraversalStep(edgeToNextNode.other(currentNode), edgeToNextNode))
                    .collect(Collectors.toList());

            addPossibleTraversals(nextSteps);

            return toReturn;
        }

        throw new NoSuchElementException("Graph is fully traversed");
    }

    @Override
    public void markAsVisited(TraversalStep step) {
        this.visitedNodes.add(step.node());
    }

    @Override
    public boolean isNotVisited(TraversalStep step) {
        return !visitedNodes.contains(step.node());
    }

    public abstract void addPossibleTraversals(Collection<TraversalStep> nextPossible);

    public abstract Optional<TraversalStep> selectAndRemoveNextCandidate(); // TODO: rename to popNextCandidate?

    private List<TraversalStep> getTraversalsFromInitialNodes() {
        return initialNodes.stream()
                .map(TraversalStep::<N, E>fromInitialNode)
                .collect(Collectors.toList());
    }

    @Override
    public GraphTraversal copy() {
        try {
            return (GraphTraversal) this.getClass().getConstructors()[0].newInstance(this.graph);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            // TODO: do something
            return null;
        }
    }
}
