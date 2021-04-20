package algorithms.search;

import algorithms.traverse.Edge;
import model.node.Node;

import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private final List<Edge> stepsToDestination;

    public Path(List<Edge> stepsToDestination) {
        this.stepsToDestination = stepsToDestination;
    }

    public Iterable<Edge> stepsToDestination() {
        return this.stepsToDestination; // TODO: Safely copy?
    }

    public Iterable<Node> nodesToDestination() {
        return this.stepsToDestination.stream()
                .map(Edge::destination)
                .collect(Collectors.toList());
    }
//
//    public Iterable<Node> edgesToDestination() {
//        return this.stepsToDestination.stream()
//                .map(TraversalStep::source)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }
}
