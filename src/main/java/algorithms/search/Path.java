package algorithms.search;

import algorithms.traverse.TraversalStep;
import model.edge.Edge;
import model.node.Node;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Path {
    private final List<TraversalStep> stepsToDestination;

    public Path(List<TraversalStep> stepsToDestination) {
        this.stepsToDestination = stepsToDestination;
    }

    public Iterable<TraversalStep> stepsToDestination() {
        return this.stepsToDestination; // TODO: Safely copy?
    }

    public Iterable<Node> nodesToDestination() {
        return this.stepsToDestination.stream()
                .map(TraversalStep::node)
                .collect(Collectors.toList());
    }

    public Iterable<Edge> edgesToDestination() {
        return this.stepsToDestination.stream()
                .map(TraversalStep::edgeToNode)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
