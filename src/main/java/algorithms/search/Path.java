package algorithms.search;

import algorithms.traverse.TraversalStep;
import model.Edge;
import model.Node;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Path<N extends Node, E extends Edge<N>> {
    private final List<TraversalStep<N, E>> stepsToDestination;

    public Path(List<TraversalStep<N, E>> stepsToDestination) {
        this.stepsToDestination = stepsToDestination;
    }

    public Iterable<TraversalStep<N, E>> stepsToDestination() {
        return this.stepsToDestination;
    }

    public Iterable<N> nodesToDestination() {
        return this.stepsToDestination.stream()
                .map(TraversalStep::node)
                .collect(Collectors.toList());
    }

    public Iterable<E> edgesToDestination() {
        return this.stepsToDestination.stream()
                .map(TraversalStep::edgeToNode)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
