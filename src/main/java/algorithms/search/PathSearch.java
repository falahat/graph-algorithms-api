package algorithms.search;

import algorithms.traverse.GraphTraversal;
import algorithms.traverse.TraversalStep;
import model.graph.Graph;
import model.graph.LabeledGraph;
import model.node.Node;

import java.util.*;
import java.util.function.Predicate;

// TODO: Clean this class for code-clarity, it doesn't seem very elegant
public class PathSearch<V extends Cloneable> {
    private final Graph<V> graph;
    private final Node startNode;
    private final Predicate<TraversalStep> required;
    private final GraphTraversal traversal;

    public PathSearch(Graph<V> graph, Node start, Predicate<TraversalStep> required, GraphTraversal traversal) {
        this.graph = graph;
        this.startNode = start;
        this.required = required;
        this.traversal = traversal;
    }

    public Optional<Path> search() {
        StepLabeledGraph<V> pathToSourceGraph = new StepLabeledGraph<>(graph);

        List<TraversalStep> stepsToDestination = findFirstMatchingStep(pathToSourceGraph)
                .map(destination -> getReversePathToSource(pathToSourceGraph, destination))
                .orElse(Collections.emptyList());

        if (!stepsToDestination.isEmpty() && stepsToDestination.get(0).node().equals(startNode)) {
            return Optional.of(new Path(stepsToDestination));
        }

        return Optional.empty();
    }

    private Optional<TraversalStep> findFirstMatchingStep(StepLabeledGraph<V> pathToSource) {
        TraversalStep destination = null;
        for (TraversalStep step : traversal) {
            pathToSource.setLabel(step.node(), step);

            // Destination found, now retrace steps back to source
            if (this.required.test(step)) {
                destination = step;
                break;
            }
        }
        return Optional.ofNullable(destination);
    }

    private List<TraversalStep> getReversePathToSource(StepLabeledGraph<V> pathToSource,
                                                       TraversalStep destination) {
        Set<Node> visitedNodes = new HashSet<>();
        TraversalStep currentStep = destination;
        List<TraversalStep> stepsToDestination = new Stack<>();
        stepsToDestination.add(currentStep);
        while (currentStep != null // This should be impossible unless the search algorithm is broken
                && !currentStep.node().equals(startNode) // We reached the source and have a full path from source to dest.
                && !visitedNodes.contains(currentStep.node()) // We hit a cycle for some reason. Exit now. If one of the nodes in the cycle was the source, we would have already exited.
                && currentStep.edgeToNode().isPresent()) { // If the current step has no edge to it, we have no way of seeing edges behind it. In this case, it must be source or we do not have a path

            stepsToDestination.add(currentStep);
            visitedNodes.add(currentStep.node());
            Node previousNode = currentStep.edgeToNode().get().other(currentStep.node());
            currentStep = pathToSource.getLabel(previousNode).orElse(null);
        }
        return stepsToDestination;
    }

    public static class StepLabeledGraph<N extends Cloneable> extends LabeledGraph<N, TraversalStep> {
        public StepLabeledGraph(Graph<N> innerGraph) {
            super(innerGraph);
        }
    }
}
