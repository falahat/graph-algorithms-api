package algorithms.search;

import algorithms.traverse.GraphTraversal;
import algorithms.traverse.Edge;
import model.graph.Graph;
import model.node.Node;

import java.util.function.Predicate;

// TODO: Clean this class for code-clarity, it doesn't seem very elegant
public class PathSearch {
    private final Graph graph;
    private final Node startNode;
    private final Predicate<Edge> required;
    private final GraphTraversal traversal;

    public PathSearch(Graph graph, Node start, Predicate<Edge> required, GraphTraversal traversal) {
        this.graph = graph;
        this.startNode = start;
        this.required = required;
        this.traversal = traversal;
    }
    // TODO: we may re-write this to not use Edge, but just nodes
//
//    public Optional<Path> search() {
//        StepLabeledGraph pathToSourceGraph = new StepLabeledGraph<>(graph);
//
//        List<Edge> stepsToDestination = findFirstMatchingStep(pathToSourceGraph)
//                .map(destination -> getReversePathToSource(pathToSourceGraph, destination))
//                .orElse(Collections.emptyList());
//
//        if (!stepsToDestination.isEmpty() && stepsToDestination.get(0).destination().equals(startNode)) {
//            return Optional.of(new Path(stepsToDestination));
//        }
//
//        return Optional.empty();
//    }
//
//    private Optional<Edge> findFirstMatchingStep(StepLabeledGraph<V> pathToSource) {
//        Edge destination = null;
//        for (Edge step : traversal) {
//            pathToSource.setLabel(step.destination(), step);
//
//            // Destination found, now retrace steps back to source
//            if (this.required.test(step)) {
//                destination = step;
//                break;
//            }
//        }
//        return Optional.ofNullable(destination);
//    }
//
//    private List<Edge> getReversePathToSource(StepLabeledGraph<Edge> pathToSource,
//                                              Edge destination) {
//        Set<Node> visitedNodes = new HashSet<>();
//        Edge currentStep = destination;
//        List<Edge> stepsToDestination = new Stack<>();
//        stepsToDestination.add(currentStep);
//        while (currentStep != null // This should be impossible unless the search algorithm is broken
//                && !currentStep.destination().equals(startNode) // We reached the source and have a full path from source to dest.
//                && !visitedNodes.contains(currentStep.destination()) // We hit a cycle for some reason. Exit now. If one of the nodes in the cycle was the source, we would have already exited.
//                && currentStep.source() != null) { // If the current step has no edge to it, we have no way of seeing edges behind it. In this case, it must be source or we do not have a path
//
//            stepsToDestination.add(currentStep);
//            visitedNodes.add(currentStep.destination());
//            Node previousNode = currentStep.source().get().other(currentStep.destination());
//            currentStep = pathToSource.getLabel(previousNode).orElse(null);
//        }
//        return stepsToDestination;
//    }
//
//    public static class StepLabeledGraph<N extends Cloneable> extends LabeledGraph<Edge> {
//        public StepLabeledGraph(Graph innerGraph) {
//            super(innerGraph);
//        }
//    }
}
