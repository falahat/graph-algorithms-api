package model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Graph<N extends Node, E extends Edge<N>> {

    boolean contains(N node);

    void add(N node);
    void add(E edge);

    void remove(N node);
    void remove(E edge);

    Collection<N> nodes();

    Collection<E> edges();

    Collection<E> edges(N node);

    default List<N> neighbors(N node) {
        return edges(node)
                .stream()
                .filter(edge -> !edge.isDirected() || node.equals(edge.node1()))
                .map(edge -> edge.other(node))
                .collect(Collectors.toList());
    }
}
