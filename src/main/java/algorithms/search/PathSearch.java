package algorithms.search;

import algorithms.traverse.GraphTraversal;
import algorithms.traverse.TraversalStep;
import model.edge.Edge;
import model.graph.Graph;
import model.graph.LabeledGraph;
import model.node.Node;

import java.util.*;
import java.util.function.Predicate;

// TODO: Clean this class for code-clarity, it doesn't seem very elegant
public class PathSearch<N extends Node, E extends Edge<N>> {
    private final Graph<N, E> graph;
    private final N startNode;
    private final Predicate<TraversalStep<N, E>> required;
    private final GraphTraversal<N, E> traversal;

    public PathSearch(Graph<N, E> graph, N start, Predicate<TraversalStep<N, E>> required, GraphTraversal<N, E> traversal) {
        this.graph = graph;
        this.startNode = start;
        this.required = required;
        this.traversal = traversal;
    }

    public Optional<Path<N, E>> search() {
        StepLabeledGraph<N, E> pathToSourceGraph = new StepLabeledGraph<>(graph);

        List<TraversalStep<N, E>> stepsToDestination = findFirstMatchingStep(pathToSourceGraph)
                .map(destination -> getReversePathToSource(pathToSourceGraph, destination))
                .orElse(Collections.emptyList());

        if (!stepsToDestination.isEmpty() && stepsToDestination.get(0).node().equals(startNode)) {
            return Optional.of(new Path<>(stepsToDestination));
        }

        return Optional.empty();
    }

    private Optional<TraversalStep<N, E>> findFirstMatchingStep(StepLabeledGraph<N, E> pathToSource) {
        TraversalStep<N, E> destination = null;
        for (TraversalStep<N, E> step : traversal) {
            pathToSource.setLabel(step.node(), step);

            // Destination found, now retrace steps back to source
            if (this.required.test(step)) {
                destination = step;
                break;
            }
        }
        return Optional.ofNullable(destination);
    }

    private List<TraversalStep<N, E>> getReversePathToSource(StepLabeledGraph<N, E> pathToSource,
                                                             TraversalStep<N, E> destination) {
        Set<N> visitedNodes = new HashSet<>();
        TraversalStep<N, E> currentStep = destination;
        List<TraversalStep<N, E>> stepsToDestination = new Stack<>();
        stepsToDestination.add(currentStep);
        while (currentStep != null // This should be impossible unless the search algorithm is broken
                && !currentStep.node().equals(startNode) // We reached the source and have a full path from source to dest.
                && !visitedNodes.contains(currentStep.node()) // We hit a cycle for some reason. Exit now. If one of the nodes in the cycle was the source, we would have already exited.
                && currentStep.edgeToNode().isPresent()) { // If the current step has no edge to it, we have no way of seeing edges behind it. In this case, it must be source or we do not have a path

            stepsToDestination.add(currentStep);
            visitedNodes.add(currentStep.node());
            N previousNode = currentStep.edgeToNode().get().other(currentStep.node());
            currentStep = pathToSource.getLabel(previousNode).orElse(null);
        }
        return stepsToDestination;
    }

    public static class StepLabeledGraph<N extends Node, E extends Edge<N>> extends LabeledGraph<N, E, TraversalStep<N, E>> {
        public StepLabeledGraph(Graph<N, E> innerGraph) {
            super(innerGraph);
        }
    }
}
